package com.tools.jj.tools.utils;

import android.app.Activity;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

/**
 * Created by jj on 2018/2/3.
 */

public class WindowsUtil {

    /**
     * 设置全屏
     * @param activity
     */
    public static void setFullScreen(Activity activity) {
        activity.requestWindowFeature(Window.FEATURE_NO_TITLE);// 隐藏标题
        activity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);// 设置全屏
    }


    /**
     * 设置状态栏透明
     *
     * @param activity
     */
    public static void setStatusViewTranslucent(Activity activity,boolean fitsSystem) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //透明状态栏
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            ViewGroup rootView = (ViewGroup) ((ViewGroup) activity.getWindow().findViewById(android.R.id.content)).getChildAt(0);
            //预留系统空间
            rootView.setFitsSystemWindows(fitsSystem);
            //绘制区域在padding里面
            rootView.setClipToPadding(true);
        }
    }

    /**
     * 为activity添加状态栏颜色
     *
     * @param activity
     * @param color
     */
    public static void addStatusViewWithColor(Activity activity, int color) {
        ViewGroup contentView = (ViewGroup) activity.findViewById(android.R.id.content);
        View statusBarView = new View(activity);
        ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                getStatusBarHeight(activity));
        statusBarView.setBackgroundResource(color);
        contentView.addView(statusBarView, lp);
    }

    /**
     * 获取状态栏高度
     *
     * @param activity
     * @return
     */
    public static int getStatusBarHeight(Activity activity) {
        int result = 0;
        //获取状态栏高度的资源id
        int resourceId = activity.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = activity.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }
}
