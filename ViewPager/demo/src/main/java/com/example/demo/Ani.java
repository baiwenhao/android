package com.example.demo;

import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.LinearLayout;

/*
 * 动画分为四种
 * AlphaAnimation 透明度动画效果
 * ScaleAnimation 缩放动画效果
 * TranslateAnimation 位移动画效果
 * RotateAnimation 旋转动画效果
 * */
public class Ani {
    // imageView控件由完全透明到完全不透明变化，持续时间为0.2s
    private void toVisibleAnim(View view)
    {
        AlphaAnimation alphaAnimation = new AlphaAnimation(0.0f, 1.0f);
        alphaAnimation.setDuration(200);
        view.startAnimation(alphaAnimation);
    }

    // imageView控件由原来大小尺寸沿自身尺寸中心逐渐缩放到0，持续时间为0.2s
    private void toHideAnim(View view)
    {
        ScaleAnimation scaleAnimation = new ScaleAnimation(1.0f, 0.0f, 1.0f, 0.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        scaleAnimation.setDuration(200);
        view.startAnimation(scaleAnimation);
    }

    // imageView控件以自身中心为圆心旋转90度，持续时间为0.2s
    private void rotateAnim(View view)
    {
        view.setVisibility(View.VISIBLE);
        RotateAnimation rotateAnimation = new RotateAnimation(0, 90, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        rotateAnimation.setDuration(200);
        view.startAnimation(rotateAnimation);
    }

    // 水平移动
    private void showScrollAnim(View view) {
        view.setVisibility(View.VISIBLE);
        TranslateAnimation mShowAction = new TranslateAnimation(
                Animation.RELATIVE_TO_SELF, 1.0f, Animation.RELATIVE_TO_SELF,
                0.0f, Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.0f);
        mShowAction.setDuration(200);
        view.startAnimation(mShowAction);
    }

    // image控件从自身位置的最左端开始水平向右滑动隐藏动画，持续时间0.2s
    private void hiddenScrollAnim(LinearLayout view) {
        view.setVisibility(View.GONE);
        TranslateAnimation mHiddenAction = new TranslateAnimation(
                Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF,
                1.0f, Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.0f);
        mHiddenAction.setDuration(200);
        view.startAnimation(mHiddenAction);
    }
    // https://blog.csdn.net/qq_20785431/article/details/101199524
}
