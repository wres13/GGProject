package com.tools.jj.tools.activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.tools.jj.tools.basemvp.p.BasePresenter;
import com.tools.jj.tools.basemvp.v.BaseView;
import com.tools.jj.tools.dto.MessageEventDto;
import com.tools.jj.tools.utils.AppManager;
import com.tools.jj.tools.view.LoadingByLottieDialog;
import com.umeng.analytics.MobclickAgent;

import org.greenrobot.eventbus.EventBus;

import butterknife.ButterKnife;

/**
 * Created by jj on 2018/2/1.
 */

public abstract class BaseActivity<T extends BasePresenter> extends AppCompatActivity implements BaseView {

    protected T mBasePresenter;
    protected Context mContext;
    protected Activity mActivity;
    protected boolean mCreate;
    private Toast mToast;
    private Dialog mLoadingDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //竖屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        mContext = this;
        mActivity = this;
        //创建视图
        int layoutId=getLayoutId();
        setContentView(layoutId);
        ButterKnife.bind(this);
        initToolBar();
        initView();
        registerListener();
        mBasePresenter = createPresenter();
        //保存当前Activity实体
        AppManager.getInstance().addActivity(mActivity);
        mCreate=true;

        if (isBindEventBusHere() && !EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        //友盟统计
        MobclickAgent.onResume(this);
        if (mCreate) {
            initData();
            mCreate = false;
        }
        if (mBasePresenter != null) {
            mBasePresenter.onResume();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }


    @Override
    protected void onStop() {
        super.onStop();
        releaseMemory();
    }

    @Override
    protected void onDestroy() {
        if (mBasePresenter != null) {
            mBasePresenter.onDestroy();
        }
        if (isBindEventBusHere() && EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
        ButterKnife.unbind(this);
        super.onDestroy();
    }

    /**
     * EventBus 子类重写该方法
     */
    protected void onEventComing(MessageEventDto event) {

    }

    /**
     * EventBus 需要注册EventBus的Activity重写该方法,返回true
     */
    protected boolean isBindEventBusHere() {
        return false;
    }




    @Override
    public void toast(final String msg) {
        //限制toast，避免用户疯狂点击出现问题
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (mToast == null) {
                    mToast = Toast.makeText(mContext, msg + "", Toast.LENGTH_SHORT);
                } else {
                    mToast.setText(msg + "");
                    mToast.setDuration(Toast.LENGTH_SHORT);
                }
                mToast.show();
            }
        });
    }

    @Override
    public void showProgress(String msg) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if(mLoadingDialog==null){
                    mLoadingDialog=new LoadingByLottieDialog(mContext);
                }
                if(!mLoadingDialog.isShowing()&&!isFinishing()){
                    mLoadingDialog.show();
                }
            }
        });
    }

    @Override
    public void hideProgress() {
        if(mLoadingDialog!=null&&mLoadingDialog.isShowing()&&!isFinishing())
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    mLoadingDialog.dismiss();
                    mLoadingDialog=null;
                }
            });
    }



    @Override
    public Context getContext() {
        return this;
    }


    /**
     *
     * @return
     */
    protected abstract T createPresenter();

    /**
     *
     * @return
     */
    protected abstract int getLayoutId();

    /**
     *
     */
    protected abstract void initView();

    /**
     *
     */
    protected abstract void initToolBar();

    /**
     *
     */
    protected abstract void initData();

    /**
     *
     */
    protected abstract void registerListener();

    /**
     *
     */
    protected abstract void releaseMemory();
}
