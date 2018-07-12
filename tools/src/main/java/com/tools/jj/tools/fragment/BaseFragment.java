package com.tools.jj.tools.fragment;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.tools.jj.tools.basemvp.p.BasePresenter;
import com.tools.jj.tools.basemvp.v.BaseView;

import com.tools.jj.tools.dto.MessageEventDto;
import com.tools.jj.tools.view.LoadingByLottieDialog;

import org.greenrobot.eventbus.EventBus;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * Created by jj on 2018/2/7.
 */

public abstract class BaseFragment<T extends BasePresenter> extends Fragment implements BaseView {

    protected T mBasePresenter;
    private Dialog mLoadingDialog;
    protected View mView;
    protected Context mContext;
    protected Activity mActivity;
    private Toast mToast;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(getLayoutId(), container, false);
        ButterKnife.bind(this, mView);
        initView();
        initToolBar();
        registerListener();
        mBasePresenter = createPresenter();
        mContext = getActivity();
        mActivity = getActivity();
        initData();
        if (isBindEventBusHere() && !EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }

        return mView;
    }


    @Override
    public void onResume() {
        super.onResume();
        if (mBasePresenter != null) {
            mBasePresenter.onResume();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mBasePresenter != null) {
            mBasePresenter.onDestroy();
        }
    }

    @Override
    public void toast(final String msg) {
        getActivity().runOnUiThread(new Runnable() {
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
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (mLoadingDialog == null) {
                    mLoadingDialog = new LoadingByLottieDialog(mContext);
                }
                if (!mLoadingDialog.isShowing() && !getActivity().isFinishing()) {
                    mLoadingDialog.show();
                }
            }
        });
    }

    @Override
    public void hideProgress() {
        if (mLoadingDialog != null && mLoadingDialog.isShowing() && !getActivity().isFinishing())
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    mLoadingDialog.dismiss();
                    mLoadingDialog = null;
                }
            });
    }



    /**
     * EventBus 需要注册EventBus的Activity重写该方法,返回true
     */
    protected boolean isBindEventBusHere() {
        return false;
    }

    /**
     * EventBus 子类重写该方法
     */
    protected void onEventComing(MessageEventDto event) {

    }


    protected abstract void initView();

    /**
     *
     *
     */
    protected abstract int getLayoutId();

    protected abstract T createPresenter();

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
}
