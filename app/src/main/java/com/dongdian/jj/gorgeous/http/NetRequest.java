package com.dongdian.jj.gorgeous.http;

import android.content.Context;

import com.dongdian.jj.gorgeous.dto.ImageData;
import com.dongdian.jj.gorgeous.dto.PostListDto;
import com.dongdian.jj.gorgeous.dto.UserDto;
import com.tools.jj.tools.http.Http;
import com.tools.jj.tools.http.IReponseListener;
import com.tools.jj.tools.http.JsonResult;
import com.tools.jj.tools.http.RequestSubscriber;
import com.tools.jj.tools.utils.LogUtil;

import java.util.List;
import java.util.Map;

import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;


/**
 * Created by jj on 2018/2/1.
 */

public class NetRequest {

    private volatile static NetRequest instance = null;

    private Api api;

    public static NetRequest getInstance() {
        if (instance == null) {
            synchronized (NetRequest.class) {
                if (instance == null) {
                    instance = new NetRequest();
                }
            }
        }
        return instance;
    }

    private NetRequest() {
        api = Http.createApi(Api.class);
    }


//    public void getPostList(Context context, IReponseListener<List<PostListDto>> listener) {
//        api.getPostList()
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new RequestSubscriber<>(context, true, listener));
//    }
//
//    public void getPostListLogin(Context context, int userId, IReponseListener<List<PostListDto>> listener) {
//        api.getPostListLogin(userId)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new RequestSubscriber<>(context, true, listener));
//    }
//
//    public void login(Context context, Map<String, String> map, IReponseListener<UserDto> listener) {
//        api.login(map)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new RequestSubscriber<>(context, true, listener));
//    }

    public void getRecommendImageList(Context context, IReponseListener<ImageData> listener, int type, int page) {
        api.getImgeList(type,page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RequestSubscriber<>(context, true, listener));
    }

}
