package com.tools.jj.tools.basemvp.p;/**
 * Created by fengyin on 7/11/16.
 */

import com.tools.jj.tools.basemvp.v.BaseView;

/**
 * Created by Jiajun he on 2018/3/28.
 * Email:1021661582@qq.com
 * des:
 * version: 1.0.0
 */
public abstract class BasePresenterImpl<T extends BaseView> implements BasePresenter {
    protected T mView;

    public BasePresenterImpl(T view){
        attachView(view);
    }

    protected T getView(){
        return mView;
    }

    public boolean isViewAttached(){
        return mView != null;
    }

    private void detachView(){
        if(mView != null){
            mView = null;
        }
    }
    private void attachView(T mView){
        this.mView=mView;
    }

    @Override
    public void onDestroy() {
        detachView();
    }


}
