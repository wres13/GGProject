package com.tools.jj.tools.http;


import android.util.Log;

import java.io.IOException;
import java.nio.charset.Charset;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.RequestBody;
import okio.Buffer;

/**
 * Created by zhangdm on 2016/4/20.
 */
public class RequestHeaderInterceptor implements Interceptor {

    private static final Charset UTF8 = Charset.forName("UTF-8");

    @Override
    public okhttp3.Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        RequestBody requestBody = request.body();
        Request.Builder builder = request.newBuilder();
//        if (!CheckUtil.isNull(Http.user_session)) {
//            builder.addHeader("sessionId", Http.user_session);
//            builder.addHeader("token", Http.user_token);
//            builder.addHeader("sign", StringUtil.MD5(Http.user_session + "qrwd+gzcxzc-s454545+8mm7dfsdafhhoopqq-x"));
//        } else {
//            builder.addHeader("sign", StringUtil.MD5("qrwd+gzcxzc-s454545+8mm7dfsdafhhoopqq-x"));
//        }

        //记录请求头信息
        StringBuffer headStr = new StringBuffer();
//        //添加头信息
//        if (Hawk.contains(HawkKey.SESSION_ID)) {
//            String sessionId = Hawk.get(HawkKey.SESSION_ID);
//            LogUtil.e("sessionId=" +sessionId);
//            builder.addHeader("sessionId", sessionId);
//            if (headStr.length() == 0) {
//                headStr.append("sessionId=" + sessionId);
//            } else {
//                headStr.append("&");
//                headStr.append("sessionId=" + sessionId);
//            }
//        }
        //打印请求信息
        String requestMessage;
        requestMessage = request.method() + ' ' + request.url();
        if (requestBody != null) {
            Buffer buffer = new Buffer();
            requestBody.writeTo(buffer);
            requestMessage += "?" + headStr + ' ' + buffer.readString(UTF8);
        }
        Log.e("请求信息", requestMessage);

        return chain.proceed(builder.build());
    }

}