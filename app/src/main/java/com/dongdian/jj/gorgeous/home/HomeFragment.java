package com.dongdian.jj.gorgeous.home;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;

import com.dongdian.jj.gorgeous.R;
import com.dongdian.jj.gorgeous.home.adapter.HomePagerAdapter;
import com.dongdian.jj.gorgeous.home.presenter.HomePresenterImpl;
import com.dongdian.jj.gorgeous.home.presenter.IHomePrensenter;
import com.dongdian.jj.gorgeous.home.view.IHomeView;
import com.dongdian.jj.gorgeous.home.view.RecommendFragment;
import com.tools.jj.tools.fragment.BaseFragment;



import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Jiajun he on 2018/4/8.
 * Email:1021661582@qq.com
 * des:
 * version: 1.0.0
 */

public class HomeFragment extends BaseFragment<IHomePrensenter> implements IHomeView {

    @Bind(R.id.home_vp)
    ViewPager home_vp;
    @Bind(R.id.tb_home)
    TabLayout tbHome;

    private String[] indicatorText;
    private HomePagerAdapter homePagerAdapter;
    private List<Fragment> fragmentList = new ArrayList<>();

    @Override
    public void showRequestFail() {

    }

    @Override
    public void showRequestSuccess() {

    }

    @Override
    protected void initView() {
        initViewPager();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
//        initIndicator();
    }

    private void initViewPager() {
        //标题
        indicatorText = getResources().getStringArray(R.array.main_home_tab_category);
        for (int i = 0; i < 3; i++) {
            Fragment recommendFragment = new RecommendFragment();
            fragmentList.add(recommendFragment);
        }
        if (homePagerAdapter == null) {
            homePagerAdapter = new HomePagerAdapter(getChildFragmentManager(), indicatorText, fragmentList);
            home_vp.setAdapter(homePagerAdapter);
        }
        tbHome.setupWithViewPager(home_vp);
        //设置tab可以滑动
        //tbHome.setTabMode(TabLayout.MODE_SCROLLABLE);

    }



    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected IHomePrensenter createPresenter() {
        mBasePresenter = new HomePresenterImpl(this);
        return mBasePresenter;
    }

    @Override
    protected void initToolBar() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void registerListener() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
