package com.tools.jj.tools.http;

import android.content.Context;


import com.franmontiel.persistentcookiejar.ClearableCookieJar;
import com.franmontiel.persistentcookiejar.PersistentCookieJar;
import com.franmontiel.persistentcookiejar.cache.SetCookieCache;
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by longbh on 16/5/24.
 */
public class Http {

    public final static long DEFAULT_TIMEOUT = 30;

    public static String user_session ;

    public static String user_token = "";

//    public static String baseUrl = "http://119.29.0.81:1010/digitallive/";
//    public static String baseUrl = "http://47.52.75.111:7012/digitallive/";
//     public static String baseUrl = "http://119.29.157.217:8111/api/";
//     public static String baseImageUrl="http://119.29.157.217:8111";
//    public static String ImageUrlList="http://www.chennaicha.club/";
//	public static String baseUrl = "https://www.heartwish.com.tw:443/digitallive/";
//    public  static final String BASE_URL="http://www.chennaicha.club/gogerous/";
    public  static final String BASE_IMAGE_URL="http://www.chennaicha.club/gogerous";
    public  static final String BASE_URL="http://gank.io/api/data/";
    public static Http http;


    private static Retrofit mRetrofit;


    public static void initHttp(Context context) {
        http = new Http(context);

    }

    public Http(Context context) {
        ClearableCookieJar cookieJar = new PersistentCookieJar(new SetCookieCache(), new SharedPrefsCookiePersistor(context));
        OkHttpClient  client = new OkHttpClient.Builder()
                .addInterceptor(new RequestLogInterceptor())
                .addInterceptor(new RequestHeaderInterceptor())
//                .cookieJar(cookieJar)
//                .addInterceptor(new ReadCookiesInterceptor())
//                .addInterceptor(new SaveSessionInterceptor())
                .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .build();
        mRetrofit = new Retrofit.Builder()
                .client(client)
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }

    public static  <T> T createApi(Class<T> tClass) {
        return mRetrofit.create(tClass);
    }

}
