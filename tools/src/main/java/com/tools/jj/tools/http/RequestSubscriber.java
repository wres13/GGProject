package com.tools.jj.tools.http;


import android.app.Dialog;
import android.content.Context;

import com.tools.jj.tools.utils.LogUtil;
import com.tools.jj.tools.utils.NetworkUtil;
import com.tools.jj.tools.view.LoadingByLottieDialog;

import rx.Subscriber;

/**
 * <p>desc</p>
 *
 * @author chenqingguang
 * @version 1.0 (17/6/6)
 */
public class RequestSubscriber<T> extends Subscriber<T> {

    Context mContext;
    IReponseListener mListener;
    private Dialog mLoadingDialog;
    private boolean isShowDialog;

    public RequestSubscriber(Context context, boolean isShowDialog, IReponseListener<T> listener) {
        this.mListener = listener;
        this.mContext = context;
        this.isShowDialog = isShowDialog;
        if (isShowDialog) {
            mLoadingDialog = new LoadingByLottieDialog(context);
            mLoadingDialog.setCanceledOnTouchOutside(false);
            mLoadingDialog.show();
        }
    }


    @Override
    public void onCompleted() {
        if (isShowDialog) {
            if (mLoadingDialog != null) {
                mLoadingDialog.dismiss();
            }
        }
    }

    @Override
    public void onError(Throwable e) {
        if (isShowDialog) {
            if (mLoadingDialog != null) {
                mLoadingDialog.dismiss();
            }
        }
        LogUtil.e("响应出错:"+e.toString());
        if (!NetworkUtil.isNetworkConnected(mContext)) {
            mListener.onFail("当前网络不可用,请检查您的网络设置");
            return;
        }

        mListener.onFail("服务器异常，请稍后重试！");
    }

    @Override
    public void onNext(T tJsonResult) {
        mListener.onSuccess(tJsonResult);
    }

//    @Override
//    public void onNext(JsonResult<T> tJsonResult) {
//        LogUtil.d("响应数据："+tJsonResult.toString());
//        if(tJsonResult.getStatueCode()==200){
//            mListener.onSuccess(tJsonResult.getResults());
//        }else {
//            mListener.onFail(tJsonResult.getMessage());
//        }
//    }




}
