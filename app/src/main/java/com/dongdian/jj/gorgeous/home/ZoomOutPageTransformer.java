package com.dongdian.jj.gorgeous.home;

import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.view.View;

/**
 * Created by Administrator on 2018/4/26.
 */

public class ZoomOutPageTransformer implements ViewPager.PageTransformer {
    private static final float MAX_SCALE = 1.2f;
    private static final float MIN_SCALE = 1.0f;//0.85f
    private static final float min_scale = 0.85f;

    @Override
    public void transformPage(@NonNull View page, float position) {

        float scaleFactor = Math.max(min_scale, 1 - Math.abs(position));
        float rotate = 20 * Math.abs(position);
        if (position < -1) {

        } else if (position < 0) {
            page.setScaleX(scaleFactor);
            page.setScaleY(scaleFactor);
            page.setRotationY(rotate);
        } else if (position >= 0 && position < 1) {
            page.setScaleX(scaleFactor);
            page.setScaleY(scaleFactor);
            page.setRotationY(-rotate);
        } else if (position >= 1) {
            page.setScaleX(scaleFactor);
            page.setScaleY(scaleFactor);
            page.setRotationY(-rotate);
        }


//        //setScaleY只支持api11以上
//        if (position < -1){
//            page.setScaleX(MIN_SCALE);
//            page.setScaleY(MIN_SCALE);
//        } else if (position <= 1) //a页滑动至b页 ； a页从 0.0 -1 ；b页从1 ~ 0.0
//        { // [-1,1]
////              Log.e("TAG", view + " , " + position + "");
//            float scaleFactor =  MIN_SCALE+(1-Math.abs(position))*(MAX_SCALE-MIN_SCALE);
//            page.setScaleX(scaleFactor);
//            //每次滑动后进行微小的移动目的是为了防止在三星的某些手机上出现两边的页面为显示的情况
//            if(position>0){
//                page.setTranslationX(-scaleFactor*2);
//            }else if(position<0){
//                page.setTranslationX(scaleFactor*2);
//            }
//            page.setScaleY(scaleFactor);
//
//        } else
//        { // (1,+Infinity]
//
//            page.setScaleX(MIN_SCALE);
//            page.setScaleY(MIN_SCALE);
//
//        }
    }
}
