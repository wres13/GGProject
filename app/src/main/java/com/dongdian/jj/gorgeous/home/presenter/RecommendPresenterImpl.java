package com.dongdian.jj.gorgeous.home.presenter;

import com.dongdian.jj.gorgeous.dto.ImageData;
import com.dongdian.jj.gorgeous.dto.PostListDto;
import com.dongdian.jj.gorgeous.dto.UserDto;
import com.dongdian.jj.gorgeous.home.view.IHomeView;
import com.dongdian.jj.gorgeous.home.view.IRecommendView;
import com.dongdian.jj.gorgeous.http.NetRequest;
import com.tools.jj.tools.basemvp.p.BasePresenterImpl;
import com.tools.jj.tools.http.IReponseListener;
import com.tools.jj.tools.utils.LogUtil;
import com.tools.jj.tools.utils.StringUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/4/24.
 */

public class RecommendPresenterImpl extends BasePresenterImpl<IRecommendView> implements IRecommendPrensenter, IReponseListener<ImageData> {


    public RecommendPresenterImpl(IRecommendView view) {
        super(view);
    }

    @Override
    public void onSuccess(ImageData postListDtos) {
        if (postListDtos.isError()){
            onFail("获取数据失败");
        }else {
            if (null != getView()) {
                getView().showRecommendList(postListDtos.getResults());
            } else {
                LogUtil.d("view 为 null");
            }
        }
    }

    @Override
    public void onFail(String msg) {
        if (null != getView()) {
            LogUtil.d(msg);
        } else {
            LogUtil.d("view 为 null");
        }
    }

    @Override
    public void onResume() {

    }


    @Override
    public void getPostListNotLogin(int type,int page) {
//        Map<String,String> map=new HashMap<>();
//        map.put("account","admin");
//        map.put("password", StringUtil.MD5("adminhjj").toLowerCase());
//        NetRequest.getInstance().login(getView().getContext(), map, new IReponseListener<UserDto>() {
//            @Override
//            public void onSuccess(UserDto userDto) {
//                LogUtil.d("登录成功");
//                NetRequest.getInstance().getPostList(getView().getContext(),RecommendPresenterImpl.this);
//            }
//
//            @Override
//            public void onFail(String msg) {
//                LogUtil.d("登录出错"+msg);
//            }
//        });
        NetRequest.getInstance().getRecommendImageList(getView().getContext(),RecommendPresenterImpl.this,type,page);

    }
}
