package com.dongdian.jj.gorgeous.utils;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.dongdian.jj.gorgeous.R;

/**
 * Created by Administrator on 2018/4/10.
 */

public class ImageUtil {
    private static int[] buttonList = new int[4];
    public static void mainBtnDispose(Context context,RadioGroup radioGroup){
        buttonList[0] = R.drawable.selector_main_home_btn;
        buttonList[1] = R.drawable.selector_main_category_btn;
        buttonList[2] = R.drawable.selector_main_dynamic_btn;
        buttonList[3] = R.drawable.selector_main_msg_btn;
        for (int i = 0; i < 4; i++) {
            RadioButton rb = (RadioButton) radioGroup.getChildAt(i);
            Drawable d = ContextCompat.getDrawable(context, buttonList[i]);
            d.setBounds(0, 0, DensityUtil.dip2px(context, 25), DensityUtil.dip2px(context, 25));
            rb.setCompoundDrawables(null, d, null, null);
        }
    }
}
