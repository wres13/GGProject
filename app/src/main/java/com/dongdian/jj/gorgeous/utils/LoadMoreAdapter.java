package com.dongdian.jj.gorgeous.utils;

import android.content.Context;
import android.util.Log;

import com.alibaba.android.vlayout.LayoutHelper;
import com.dongdian.jj.gorgeous.dto.ImageData;
import com.tools.jj.tools.adapter.BaseRecyclerViewHolder;
import com.tools.jj.tools.adapter.CommonDelegateAdapter;
import com.tools.jj.tools.utils.LogUtil;

import java.util.List;

/**
 * Created by Jiajun he on 2018/4/16.
 * Email:1021661582@qq.com
 * des:
 * version: 1.0.0
 */

public class LoadMoreAdapter extends CommonDelegateAdapter {
    private Context context;


    public LoadMoreAdapter(Context mContext, int mLayoutId, List<String> mDatas, LayoutHelper helper, int itemNum) {
        super(mContext, mLayoutId, mDatas, helper, itemNum);
        this.context = mContext;
    }


    @Override
    public void convert(final BaseRecyclerViewHolder holder, Object o, int position) {

    }

    @Override
    public void convert(BaseRecyclerViewHolder holder, int position) {

    }


}
