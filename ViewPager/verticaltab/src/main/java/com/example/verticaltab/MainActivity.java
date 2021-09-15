package com.example.verticaltab;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.BounceInterpolator;
import android.view.animation.OvershootInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    ViewPager2 viewPager;
    String TAG = "home";
    ConstraintLayout i1, i2, i3;
    LinearLayout active;
    int prevTop = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);
        initEvent();
    }

    private void initEvent() {
        viewPager = findViewById(R.id.view_page);
        active = findViewById(R.id.active);
        i1 = findViewById(R.id.i1);
        i2 = findViewById(R.id.i2);
        i3 = findViewById(R.id.i3);

        i1.setOnClickListener(this);
        i2.setOnClickListener(this);
        i3.setOnClickListener(this);
        Log.d(TAG, "init");
    }

    @Override
    public void onClick(View v) {
        Log.d(TAG, "click");
        changeTab(v.getId());
    }

    public void changeTab (int position) {
        switch (position) {
            case R.id.i1:
                start(active, i1.getTop());
                prevTop = i1.getTop();
                break;
            case R.id.i2:
                start(active, i2.getTop());
                prevTop = i2.getTop();
                break;
            case R.id.i3:
                start(active, i3.getTop());
                prevTop = i3.getTop();
                break;
        }
    }

    private void start(View view, int end) {
//        Interpolator interpolator = new LinearInterpolator();//匀速
//        Interpolator interpolator = new AccelerateInterpolator();//先慢后快
//        Interpolator interpolator = new AnticipateInterpolator();//开始回弹效果
//        Interpolator interpolator = new BounceInterpolator();//结束回弹效果
//        Interpolator interpolator = new CycleInterpolator(2);//跳一跳效果
//        Interpolator interpolator = new OvershootInterpolator(1);//动画结束时向前弹一定距离再回到原来位置
//        Interpolator interpolator = new AccelerateDecelerateInterpolator();//系统默认的动画效果，先加速后减速
//        Interpolator interpolator = new AnticipateOvershootInterpolator();//开始之前向前甩，结束的时候向后甩
        view.setVisibility(View.VISIBLE);
        TranslateAnimation ani = new TranslateAnimation(0,0,prevTop,end);
        Log.d(TAG, "起点=" + prevTop + "-" + "终点=" + end);
        ani.setInterpolator(new OvershootInterpolator(1));
        ani.setDuration(200);
        ani.setFillEnabled(true);
        ani.setFillAfter(true);
        view.startAnimation(ani);

        ani.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                Log.d(TAG, "begin");
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Log.d(TAG, "finish");
//                view.clearAnimation();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                Log.d(TAG, "repeat");
            }
        });
    }
}
