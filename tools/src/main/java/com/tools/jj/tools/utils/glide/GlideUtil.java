package com.tools.jj.tools.utils.glide;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.DrawableRequestBuilder;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;


/**
 * Summary ：加载网络图片使用的工具类，基于Glide
 * Created by zhangdm on 2016/2/20.
 * change by hjj on 2018/3/28
 */
public class GlideUtil {
    public static final String TAG = "GlideUtil";

    /**
     * Glide的请求管理器类
     */
    private static RequestManager mRequestManager;
    private static Context mContext;

    /**
     * 初始化Glide工具
     *
     * @param context
     */
    public static void init(Context context) {
        mContext = context;
        mRequestManager = Glide.with(context);
    }

    /**
     * Glide工具类是否已经初始化
     *
     * @return 已初始化则返回true
     */
    public static boolean isInit() {
        if (mContext == null || mRequestManager == null) {
            Log.i(TAG, TAG + "not init");
            return false;
        }
        return true;
    }


    /**
     * 加载网络图片
     *
     * @param url
     * @param imageView
     * @param defualtId 默认图片，不需要传入-1
     */
    public static void loadPicture(String url, ImageView imageView, int defualtId) {
        if (!isInit()) {
            return;
        }
        if (imageView == null) {
            return;
        }
        DrawableRequestBuilder builder = mRequestManager
                .load(url)
                .dontAnimate();
        if (defualtId != -1) {
            builder = builder.placeholder(defualtId);
        }
        builder.into(imageView);
    }


    /**
     * 加载正方形的本地图片
     *
     * @param imageView 目标控件
     */
    public static void loadPicture(int res, ImageView imageView) {
        if (!isInit()) {
            return;
        }
        if (imageView == null) {
            return;
        }
        mRequestManager.load(res).diskCacheStrategy(DiskCacheStrategy.SOURCE).into(imageView);
    }


    /**
     * 加载网络图片转为bitmap
     */
    public static void asynLoadBitmapForUrl(String url, final OnLoadBitmapFromUrlListener listener) {
        mRequestManager.load(url).asBitmap().into(new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                if (listener != null) {
                    listener.onLoadBitmapFromUrl(resource);
                }
            }
        });
    }

    public interface OnLoadBitmapFromUrlListener {
        void onLoadBitmapFromUrl(Bitmap bitmap);
    }


    //加载圆角的图片
    public static void loadRoundPicture(String url, ImageView imageView, float dp, int defaultImage) {
        DrawableRequestBuilder builder = mRequestManager
                .load(url)
                .dontAnimate()
                .transform(new GlideRoundTransform(mContext, dp));
        if (defaultImage != -1) {
            builder.placeholder(defaultImage);
        }

        builder.into(imageView);

    }


    //加载圆形的图片
    public static void loadCirclePicture(String url, ImageView imageView, int defaultImg) {
        if (!isInit()) {
            return;
        }

        if (imageView == null) {
            return;
        }

        DrawableRequestBuilder builder = mRequestManager
                .load(url)
                .dontAnimate().bitmapTransform(new CropCircleTransformation(mContext));

        if (defaultImg != -1) {
            builder = builder.placeholder(defaultImg);
        }

        builder.into(imageView);
    }


    //加载高斯模糊图片
    public static void loadBlurPicture(String url, ImageView imageView, int defaultImg) {
        if (!isInit()) {
            return;
        }

        if (imageView == null) {
            return;
        }
        DrawableRequestBuilder builder = mRequestManager
                .load(url)
                .dontAnimate().bitmapTransform(new BlurTransformation(mContext, 12, 1));// “23”：设置模糊度(在0.0到25.0之间)，默认”25";"4":图片缩放比例,默认“1”。

        if (defaultImg != -1) {
            builder = builder.placeholder(defaultImg);
        }
        builder.into(imageView);
    }


    /**
     * 清理缓存
     */
    public static void clear() {
        Glide.get(mContext).clearMemory();
        Glide.get(mContext).clearDiskCache();
    }
}
