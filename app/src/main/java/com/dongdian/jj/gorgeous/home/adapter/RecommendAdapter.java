package com.dongdian.jj.gorgeous.home.adapter;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;

import com.alibaba.android.vlayout.LayoutHelper;
import com.dongdian.jj.gorgeous.R;
import com.dongdian.jj.gorgeous.dto.ImageData;
import com.dongdian.jj.gorgeous.dto.PostListDto;
import com.dongdian.jj.gorgeous.home.ImageDetailsActivity;
import com.tools.jj.tools.adapter.BaseRecyclerViewHolder;
import com.tools.jj.tools.adapter.CommonDelegateAdapter;
import com.tools.jj.tools.http.Http;
import com.tools.jj.tools.utils.LogUtil;

import java.util.List;

/**
 * Created by Jiajun he on 2018/4/16.
 * Email:1021661582@qq.com
 * des:
 * version: 1.0.0
 */

public class RecommendAdapter extends CommonDelegateAdapter {
    private Context context;


    public RecommendAdapter(Context mContext,int mLayoutId, List<ImageData.ResultsBean> mDatas, LayoutHelper helper, int itemNum) {
        super(mContext, mLayoutId, mDatas, helper, itemNum);
        this.context = mContext;
    }


    @Override
    public void convert(final BaseRecyclerViewHolder holder, Object o, int position) {
        final ImageData.ResultsBean postListDto= (ImageData.ResultsBean) o;
        final String imageUrl=postListDto.getUrl();
        LogUtil.d(imageUrl);
        Log.d("sdadsad","url:"+imageUrl);
        holder.setImageUrl(R.id.recommend_share_image,imageUrl);
        holder.setOnClickListener(R.id.recommend_share_ll, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ImageDetailsActivity.class);
                intent.putExtra("share_image", imageUrl + "");
                intent.putExtra("share_image_width", holder.getView(R.id.recommend_share_image).getWidth() + "");
                intent.putExtra("share_image_height", holder.getView(R.id.recommend_share_image).getHeight() + "");
                context.startActivity(intent, ActivityOptions.makeSceneTransitionAnimation((Activity) context, holder.getView(R.id.recommend_share_image), "transition_share_image").toBundle());

            }
        });
    }

    @Override
    public void convert(BaseRecyclerViewHolder holder, int position) {

    }


}
