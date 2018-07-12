package com.dongdian.jj.gorgeous.http;

import com.dongdian.jj.gorgeous.dto.ImageData;
import com.dongdian.jj.gorgeous.dto.PostListDto;
import com.dongdian.jj.gorgeous.dto.UserDto;
import com.tools.jj.tools.http.JsonResult;

import java.util.List;
import java.util.Map;

import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Administrator on 2018/4/24.
 */

public interface Api {

    /**
     * 获取发图图片列表,未登陆的时候使用
     * 该接口不能判断用户是否已经添加过喜欢
     *
     * @return
     */
    @GET(Url.GET_POST_LIST_NOT_LOGIN)
    Observable<JsonResult<List<PostListDto>>> getPostList();

    /**
     * 获取发图图片列表,登录的时候使用
     *
     * @return
     */
    @GET(Url.GET_POST_LIST_LOGIN)
    Observable<JsonResult<List<PostListDto>>> getPostListLogin(@Query("userId") int userId);

    /**
     * 用户登录
     * @return
     */
    @POST(Url.LOGIN)
    @FormUrlEncoded
    Observable<JsonResult<UserDto>> login(@FieldMap Map<String,String> map);


    @GET
    Observable<JsonResult<String>> test(@retrofit2.http.Url String url);

    @GET("%E7%A6%8F%E5%88%A9/{type}/{page}")
    Observable<ImageData> getImgeList(@Path("type") int type, @Path("page") int page);
}
