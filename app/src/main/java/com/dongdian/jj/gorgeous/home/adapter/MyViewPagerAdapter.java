package com.dongdian.jj.gorgeous.home.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.dongdian.jj.gorgeous.MainActivity;
import com.dongdian.jj.gorgeous.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/4/26.
 */

public class MyViewPagerAdapter extends PagerAdapter{

    private String[] imageList;
    private Context context;

    private List<String> dataList=new ArrayList<>();

    public MyViewPagerAdapter(Context context, List<String> dataList) {
        this.context = context;
        this.dataList.addAll(dataList);

    }

    @Override
    public int getCount() {
        return dataList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return (view == object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView imageView = new ImageView(context);
        imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        imageView.setImageResource(R.drawable.rb);
        ((ViewPager) container).addView(imageView);
        return imageView;

    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ((ViewPager) container).removeView((ImageView) object);
    }


}
