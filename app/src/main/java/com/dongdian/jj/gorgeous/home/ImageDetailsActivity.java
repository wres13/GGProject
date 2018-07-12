package com.dongdian.jj.gorgeous.home;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.transition.Fade;
import android.transition.Slide;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.dongdian.jj.gorgeous.R;
import com.dongdian.jj.gorgeous.home.adapter.MyViewPagerAdapter;
import com.dongdian.jj.gorgeous.utils.DensityUtil;
import com.dongdian.jj.gorgeous.utils.GuiUtils;
import com.makeramen.roundedimageview.RoundedImageView;
import com.tools.jj.tools.activity.BaseActivity;
import com.tools.jj.tools.basemvp.p.BasePresenter;
import com.tools.jj.tools.utils.DeviceUtil;
import com.tools.jj.tools.utils.glide.GlideUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * Created by Administrator on 2018/4/20.
 */

public class ImageDetailsActivity extends BaseActivity {

    @Bind(R.id.imagedetails_image)
    ImageView mmImage;
    @Bind(R.id.imagedetails_share_image)
    ImageView shareImage;
    @Bind(R.id.imagedetails_ll)
    RelativeLayout llCircularRevealGroup;
    @Bind(R.id.imagedetails_vp)
    ViewPager viewPager;
    @Bind(R.id.imagedetails_vp_parentlayout)
    LinearLayout viewPagerParentLayout;
    @Bind(R.id.imagedetails_goback_btn)
    RoundedImageView backBtn;


    private String image;
    private int imageWidth;
    private int imageHeight;
    private int pagerWidth;
    private int currentPosition = 0;
    private List<String> dataList;

    @Override
    public void showRequestFail() {

    }

    @Override
    public void showRequestSuccess() {

    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_imagedetails;
    }

    @Override
    protected void initView() {
        image = getIntent().getStringExtra("share_image");
        imageWidth = Integer.parseInt(getIntent().getStringExtra("share_image_width"));
        imageHeight = Integer.parseInt(getIntent().getStringExtra("share_image_height"));
        initLayout();
        final Transition ts = TransitionInflater.from(this).inflateTransition(R.transition.fabtransition);
        getWindow().setSharedElementEnterTransition(ts);
        getWindow().setSharedElementExitTransition(ts);
        ts.addListener(new Transition.TransitionListener() {
            @Override
            public void onTransitionStart(Transition transition) {

            }

            @Override//过渡结束
            public void onTransitionEnd(Transition transition) {
                ts.removeListener(this);
                animateRevealShow();//执行CircleReveal动画

            }

            @Override
            public void onTransitionCancel(Transition transition) {

            }

            @Override
            public void onTransitionPause(Transition transition) {

            }

            @Override
            public void onTransitionResume(Transition transition) {

            }
        });
//        initSkipTransition();
    }

    // 动画展示
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void animateRevealShow() {
        int radius=imageHeight>=imageWidth?imageWidth:imageHeight;
        GuiUtils.animateRevealShow(
                ImageDetailsActivity.this, llCircularRevealGroup,shareImage, radius, new GuiUtils.OnRevealAnimationListener() {
                    @Override
                    public void onRevealHide() {

                    }

                    @Override
                    public void onRevealShow() {
                        shareImage.setVisibility(View.GONE);
                    }
                });
    }

    private void initLayout() {


//            Animation animation = AnimationUtils.loadAnimation(this, android.R.anim.fade_in);
//            animation.setDuration(300);
//        llCircularRevealGroup.setVisibility(View.VISIBLE);
//
//            llCircularRevealGroup.startAnimation(animation);
        ViewGroup.LayoutParams shareImageParams = shareImage.getLayoutParams();
        shareImageParams.width = imageWidth;
        shareImageParams.height = imageHeight;
        shareImage.setLayoutParams(shareImageParams);
        GlideUtil.loadPicture(image, shareImage, -1);
        GlideUtil.loadPicture(image, mmImage, -1);
        initViewPager();

    }

    private void initViewPager() {
//        LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(
//                DeviceUtil.getWindowWidth(this)*5/10,
//                DeviceUtil.getWindowHeight(this)*3/10);

        pagerWidth = (int) (getResources().getDisplayMetrics().widthPixels * 3.0f / 5.0f);
        ViewGroup.LayoutParams lp = viewPager.getLayoutParams();
        if (lp == null) {
            lp = new ViewGroup.LayoutParams(pagerWidth, ViewGroup.LayoutParams.MATCH_PARENT);
        } else {
            lp.width = pagerWidth;
        }
        viewPager.setLayoutParams(lp);
//        RelativeLayout.LayoutParams vpParentLayoutParams= (RelativeLayout.LayoutParams) viewPagerParentLayout.getLayoutParams();
//        vpParentLayoutParams.width=DeviceUtil.getWindowWidth(this);
//        viewPagerParentLayout.setLayoutParams(vpParentLayoutParams);

        viewPager.setClipChildren(false);
        viewPagerParentLayout.setClipChildren(false);
        //设置ViewPager切换效果，即实现画廊效果
        viewPager.setPageTransformer(true, new ZoomOutPageTransformer());
        dataList = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            dataList.add(i + "");
        }
        if (dataList.size() > 1) {
//            添加最后一页到第一页
            dataList.add(0, dataList.get(dataList.size() - 1));
//            添加第一页(经过上行的添加已经是第二页了)到最后一页
            dataList.add(dataList.get(1));
        }
        viewPager.setAdapter(new MyViewPagerAdapter(this, dataList));
        viewPager.setOffscreenPageLimit(3);
        viewPager.setPageMargin(-50);
        viewPager.setCurrentItem(1);
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                currentPosition = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {
//        若viewpager滑动未停止，直接返回
                if (state != ViewPager.SCROLL_STATE_IDLE) return;
//        若当前为第一张，设置页面为倒数第二张
                if (currentPosition == 0) {
                    viewPager.setCurrentItem(dataList.size() - 2, false);
                } else if (currentPosition == dataList.size() - 1) {
//        若当前为倒数第一张，设置页面为第二张
                    viewPager.setCurrentItem(1, false);
                }
            }
        });
        viewPagerParentLayout.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // dispatch the events to the ViewPager, to solve the problem that we can swipe only the middle view.
                return viewPager.dispatchTouchEvent(event);
            }

        });
    }


    private void initSkipTransition() {
        Fade fade = new Fade();
        fade.setDuration(1000);
        getWindow().setEnterTransition(fade);
        getWindow().setReturnTransition(fade);
        getWindow().setAllowEnterTransitionOverlap(true);
    }

//    // 退出事件
//    @Override
//    public void onBackPressed() {
//        GuiUtils.animateRevealHide(
//                this, llCircularRevealGroup,
//                llCircularRevealGroup.getWidth() / 2,
//                new GuiUtils.OnRevealAnimationListener() {
//                    @Override
//                    public void onRevealHide() {
//                        ImageDetailsActivity.super.onBackPressed();
//                    }
//
//                    @Override
//                    public void onRevealShow() {
//
//                    }
//                });
//    }


    @Override
    protected void initToolBar() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void registerListener() {
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    @Override
    protected void releaseMemory() {

    }
}
