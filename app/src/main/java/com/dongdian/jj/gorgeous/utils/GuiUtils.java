package com.dongdian.jj.gorgeous.utils;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.annotation.ColorRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.AccelerateDecelerateInterpolator;

import com.dongdian.jj.gorgeous.R;
import com.tools.jj.tools.utils.glide.GlideUtil;

/**
 * Created by Administrator on 2018/4/26.
 */

public class GuiUtils {

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public static void animateRevealShow(final Context context,
                                         final View view,
                                         View shareView,
                                         final int startRadius,
                                         final OnRevealAnimationListener listener) {

        int cx = (int) shareView.getX()+shareView.getWidth()/2;
        int cy = (int) shareView.getY()+shareView.getHeight()/2;
        float finalRadius = (float) Math.hypot(view.getWidth(), view.getHeight());
        Animator animator = ViewAnimationUtils.createCircularReveal(view, cx, cy, startRadius, finalRadius);
        animator.setDuration(500);
        animator.setInterpolator(new AccelerateDecelerateInterpolator());
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);


            }

            @Override
            public void onAnimationStart(Animator animation) {
                listener.onRevealShow();
                super.onAnimationStart(animation);
                view.setVisibility(View.VISIBLE);
            }
        });
        animator.start();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public static void animateRevealHide(
            final Context context, final View view,
            final int finalRadius,
            final OnRevealAnimationListener listener
    ) {
        int cx = (view.getLeft() + view.getRight()) / 2;
        int cy = (view.getTop() + view.getBottom()) / 2;
        int initialRadius = view.getWidth();
        // 与入场动画的区别就是圆圈起始和终止的半径相反
        Animator anim = ViewAnimationUtils.createCircularReveal(view, cx, cy, initialRadius, finalRadius);
        anim.setDuration(300);
        anim.setInterpolator(new AccelerateDecelerateInterpolator());
        anim.addListener(new AnimatorListenerAdapter() {
            @Override public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
            }

            @Override public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                listener.onRevealHide();
                view.setVisibility(View.INVISIBLE);
            }
        });
        anim.start();
    }


   public interface OnRevealAnimationListener {
        void onRevealHide();

        void onRevealShow();
    }
}
