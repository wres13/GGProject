package com.dongdian.jj.gorgeous.home.view;

import android.support.v7.widget.RecyclerView;
import android.transition.Slide;
import android.util.Log;
import android.view.Gravity;
import android.widget.Toast;

import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.alibaba.android.vlayout.layout.GridLayoutHelper;
import com.alibaba.android.vlayout.layout.StaggeredGridLayoutHelper;
import com.dongdian.jj.gorgeous.R;
import com.dongdian.jj.gorgeous.base.BaseListFragment;
import com.dongdian.jj.gorgeous.dto.ImageData;
import com.dongdian.jj.gorgeous.dto.PostListDto;
import com.dongdian.jj.gorgeous.home.adapter.RecommendAdapter;
import com.dongdian.jj.gorgeous.home.presenter.IRecommendPrensenter;
import com.dongdian.jj.gorgeous.home.presenter.RecommendPresenterImpl;
import com.tools.jj.tools.adapter.CommonDelegateAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jiajun he on 2018/4/16.
 * Email:1021661582@qq.com
 * des: 推荐页
 * version: 1.0.0
 */
public class RecommendFragment extends BaseListFragment<IRecommendPrensenter> implements IRecommendView {

    private List<ImageData.ResultsBean> postListNotLogin = new ArrayList<>();
    private int page = 1;

    @Override
    protected void initView() {
//         initSkipTransition();
    }

    private void initSkipTransition() {
        Slide slide = new Slide();
        slide.setDuration(800);
        slide.setSlideEdge(Gravity.LEFT);
        getActivity().getWindow().setExitTransition(slide);
        getActivity().getWindow().setAllowEnterTransitionOverlap(true);
    }


    @Override
    protected IRecommendPrensenter createPresenter() {
        mBasePresenter = new RecommendPresenterImpl(this);
        return mBasePresenter;
    }

    @Override
    protected void initToolBar() {

    }

    @Override
    protected void initData() {
        mBasePresenter.getPostListNotLogin(10, 0);
    }


    @Override
    protected void registerListener() {

    }

    @Override
    protected void onPullDownToRefresh() {
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
        postListNotLogin.clear();
        page = 1;
        mBasePresenter.getPostListNotLogin(10, page);


//                //刷新完成
//                refreshLayout.setRefreshing(false);
//                Toast.makeText(mContext, "更新了 " + imageList.size() + " 条目数据", Toast.LENGTH_SHORT).show();
//            }
//
//        }, 3000);
    }

    @Override
    protected void onPullUpToRefresh() {
//        if (mRefreshAdapter.getItemCount() < 30) {
////            new Handler().postDelayed(new Runnable() {
////                @Override
////                public void run() {
////                    List<String> moreData = new ArrayList<String>();
////                    for (int i = 0; i< 10; i++) {
////                        moreData.add(i+"");
////                    }
////                    LogUtil.d("上滑加载");
////                    Toast.makeText(mContext, "加载了 "+moreData.size()+" 条目数据", Toast.LENGTH_SHORT).show();
        page = page + 1;
        mBasePresenter.getPostListNotLogin(10, page);
        Log.d("sdadas", "page:" + page);
//        recyclerView.add

////
////                }
////
////            }, 1000);
//        } else if (mRefreshAdapter.getItemCount() >= 30) {
//            Toast.makeText(mContext, "没有数据了", Toast.LENGTH_SHORT).show();
//        }


    }

    @Override
    protected CommonDelegateAdapter createListAdapter() {
        RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();
        viewPool.setMaxRecycledViews(0, 40);
        recyclerView.setRecycledViewPool(viewPool);
//        GridLayoutHelper gridLayoutHelper = new GridLayoutHelper(2);
//        //自动填充，这个最好设置为false，不然有的时候布局会乱
//        gridLayoutHelper.setAutoExpand(false);
//        //间隔
//        gridLayoutHelper.setHGap(4);
//        gridLayoutHelper.setVGap(40);
        StaggeredGridLayoutHelper staggeredGridLayoutHelper = new StaggeredGridLayoutHelper();
        staggeredGridLayoutHelper.setLane(2);
        staggeredGridLayoutHelper.setHGap(5);
        staggeredGridLayoutHelper.setVGap(5);
        staggeredGridLayoutHelper.setMarginBottom(10);

        CommonDelegateAdapter recommendAdapter = new RecommendAdapter(getActivity(), R.layout.fragment_home_vp_fragment_live, postListNotLogin, staggeredGridLayoutHelper, 0);
        return recommendAdapter;
    }

    @Override
    public void showRequestFail() {

    }

    @Override
    public void showRequestSuccess() {

    }

    @Override
    public void showRecommendList(List<ImageData.ResultsBean> postListDtos) {
        if (page == 1) {
            mRefreshAdapter.upDateData(postListDtos);
        } else {
            mRefreshAdapter.addMoreData(postListDtos);
        }

        if (refreshLayout.isRefreshing()) {
            refreshLayout.setRefreshing(false);
        }
    }
}
