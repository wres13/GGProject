package com.dongdian.jj.gorgeous.home.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cutv on 2017/9/15.
 */

public class HomePagerAdapter extends FragmentPagerAdapter {
    private String[] data;
    private List<Fragment> fragmentList = new ArrayList<>();

    public HomePagerAdapter(FragmentManager fragmentManager, String[] data, List<Fragment> fragmentList) {
        super(fragmentManager);
        this.data = data;
        this.fragmentList.addAll(fragmentList);
    }

    public void setFragmentList(List<Fragment> fragmentList) {
        this.fragmentList = fragmentList;
    }

    public void clearFragmentList() {
        this.fragmentList.clear();
    }


    @Override
    public Fragment getItem(int position) {

        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return data[position];
    }




}
