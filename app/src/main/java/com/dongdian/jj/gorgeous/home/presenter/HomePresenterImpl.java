package com.dongdian.jj.gorgeous.home.presenter;

import com.dongdian.jj.gorgeous.dto.ImageData;
import com.dongdian.jj.gorgeous.dto.PostListDto;
import com.dongdian.jj.gorgeous.home.view.IHomeView;
import com.dongdian.jj.gorgeous.http.NetRequest;
import com.tools.jj.tools.basemvp.p.BasePresenterImpl;
import com.tools.jj.tools.http.IReponseListener;

import java.util.List;

/**
 * Created by Administrator on 2018/4/24.
 */

public class HomePresenterImpl extends BasePresenterImpl<IHomeView> implements IHomePrensenter,IReponseListener<List<ImageData>> {

    public HomePresenterImpl(IHomeView view) {
        super(view);
    }

    @Override
    public void onSuccess(List<ImageData> postListDtos) {

    }

    @Override
    public void onFail(String msg) {

    }

    @Override
    public void onResume() {

    }


}
