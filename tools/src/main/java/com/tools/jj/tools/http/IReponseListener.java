package com.tools.jj.tools.http;

/**
 * Created by fengyin on 7/15/16.
 */



public interface IReponseListener<T> {

    void onSuccess(T t);

    void onFail(String msg);

}
