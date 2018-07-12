package com.tools.jj.tools.basemvp.v;/**
 * Created by fengyin on 7/11/16.
 */

import android.content.Context;

/**
 * Created by Jiajun he on 2018/3/28.
 * Email:1021661582@qq.com
 * des:
 * version: 1.0.0
 */
public interface BaseView {

    void toast(String msg);

    void showProgress(String msg);

    void hideProgress();

    void showRequestFail();

    void showRequestSuccess();

    Context getContext();

}