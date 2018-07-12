package com.tools.jj.tools.http;




import java.io.IOException;
import java.nio.charset.Charset;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;

/**
 * Created by zhangdeming on 16/4/25.
 */
public class RequestLogInterceptor implements Interceptor {

    private static final Charset UTF8 = Charset.forName("UTF-8");

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Response response = chain.proceed(request);
        RequestBody requestBody = request.body();
        ResponseBody responseBody = response.body();
        String requestMessage = "";
        requestMessage = request.method() + ' ' + request.url();
        if (requestBody != null) {
            Buffer buffer = new Buffer();
            requestBody.writeTo(buffer);
            requestMessage += "?\n" + buffer.readString(UTF8);
        }
  //      LogUtil.i("RequestLogInterceptor", requestMessage);
//        if (response.header("Set-Cookie") != null) {
//            LogUtil.e("responseBodyString",response.header("Set-Cookie") );
//            Hawk.put(HawkKey.SESSION_ID,response.header("Set-Cookie"));
//        }

        String responseBodyString = (responseBody==null?"null":responseBody.string());

     //   LogUtil.e("RequestLogInterceptor", request.method() + ' ' + request.url() + ' ' + responseBodyString);

//        return response;
       return response.newBuilder().body(ResponseBody.create(responseBody==null? MediaType.parse("application/json"):responseBody.contentType(),
                responseBodyString.getBytes())).build();
    }
}
