package com.dongdian.jj.gorgeous.home.presenter;

import com.tools.jj.tools.basemvp.p.BasePresenter;

/**
 * Created by Jiajun he on 2018/4/16.
 * Email:1021661582@qq.com
 * des:
 * version: 1.0.0
 */
public interface IRecommendPrensenter extends BasePresenter {

    void getPostListNotLogin(int type,int page);
}
