package com.dongdian.jj.gorgeous.base;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.alibaba.android.vlayout.layout.LinearLayoutHelper;
import com.alibaba.android.vlayout.layout.StaggeredGridLayoutHelper;
import com.dongdian.jj.gorgeous.R;


import com.dongdian.jj.gorgeous.home.adapter.RecommendAdapter;
import com.dongdian.jj.gorgeous.utils.LoadMoreAdapter;
import com.tools.jj.tools.adapter.CommonDelegateAdapter;
import com.tools.jj.tools.basemvp.p.BasePresenter;
import com.tools.jj.tools.basemvp.v.BaseView;
import com.tools.jj.tools.dto.MessageEventDto;
import com.tools.jj.tools.utils.LogUtil;
import com.tools.jj.tools.view.LoadingByLottieDialog;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;


/**
 * Created by jj on 2018/2/7.
 * 列表刷新父类，布局包含recyclerview+swiperefreshlayout
 */

public abstract class BaseListFragment<T extends BasePresenter> extends Fragment implements BaseView {

    protected T mBasePresenter;
    private Dialog mLoadingDialog;
    protected View mView;
    protected Context mContext;
    protected Activity mActivity;

    private Toast mToast;
    //懒加载机制
    protected boolean isViewCreated=false;
    protected boolean isVisible=false;
    //刷新
    protected RecyclerView recyclerView;
    protected SwipeRefreshLayout refreshLayout;
    private int lastVisibleItem;
    protected CommonDelegateAdapter mRefreshAdapter;
    protected DelegateAdapter mDelegateAdapter;
    //限制只做一次请求
    protected boolean isLoadData=false;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_base_list, container, false);
        ButterKnife.bind(this, mView);
        mContext = getActivity();
        mActivity = getActivity();
        initListRefresh();
        mBasePresenter = createPresenter();
        initView();
        initToolBar();
        registerListener();
        if (isBindEventBusHere() && !EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
        return mView;
    }

    private void initListRefresh() {
        if(mView!=null){
            recyclerView=mView.findViewById(R.id.rv_base_list);
            refreshLayout=mView.findViewById(R.id.rf_base_list);

            VirtualLayoutManager manager = new VirtualLayoutManager(getActivity());
            recyclerView.setLayoutManager(manager);
            mRefreshAdapter=createListAdapter();
            mDelegateAdapter = new DelegateAdapter(manager, false);
            //设置线性布局
            LinearLayoutHelper linearLayoutHelper = new LinearLayoutHelper();
            linearLayoutHelper.setItemCount(1);
            linearLayoutHelper.setMarginBottom(10);
            List<String> moreData=new ArrayList<>();
            moreData.add(String.valueOf(R.string.rv_more_layout));
            CommonDelegateAdapter loadMoreAdapter = new LoadMoreAdapter(getActivity(), R.layout.fragment_home_vp_item_more,moreData, linearLayoutHelper, 0);
            mDelegateAdapter.addAdapter(mRefreshAdapter);
            mDelegateAdapter.addAdapter(loadMoreAdapter);
            recyclerView.setAdapter(mDelegateAdapter);
            recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                    super.onScrollStateChanged(recyclerView, newState);
                    //判断RecyclerView的状态 是空闲时，同时，是最后一个可见的ITEM时才加载
                    LogUtil.d(lastVisibleItem+"---"+mRefreshAdapter.getItemCount());
                   boolean isScrollBottem= recyclerView.computeVerticalScrollExtent() + recyclerView.computeVerticalScrollOffset()>= recyclerView.computeVerticalScrollRange();
                    Log.d("sdawdawda","sss:"+isScrollBottem);
                    if(newState==RecyclerView.SCROLL_STATE_IDLE&&isScrollBottem){
                        onPullUpToRefresh();
                    }
                }
                @Override
                public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                    super.onScrolled(recyclerView, dx, dy);
                    LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                    //最后一个可见的ITEM
                    lastVisibleItem=layoutManager.findLastVisibleItemPosition();
                }
            });

            refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {
                    onPullDownToRefresh();
                }
            });
        }
    }



    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        isViewCreated=true;
        lazyLoad();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(isVisibleToUser){
            isVisible=true;
            lazyLoad();
        }else {
            isVisible=false;
        }
    }

    private void lazyLoad() {
        if (isViewCreated && isVisible) {
            initData();
            LogUtil.i("initData");
            //数据加载完毕,恢复标记,防止重复加载
            isViewCreated = false;
            isVisible = false;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mBasePresenter != null) {
            mBasePresenter.onResume();
        }
        LogUtil.i("onResume");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mBasePresenter != null) {
            mBasePresenter.onDestroy();
        }
        LogUtil.i("onDestroy");
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

    /**
     *
     */
    protected abstract void onPullDownToRefresh();

    /**
     *
     */
    protected abstract void onPullUpToRefresh();
    /**
     *需要刷新的列表控件的设配器
     */
    protected abstract CommonDelegateAdapter createListAdapter();
}
