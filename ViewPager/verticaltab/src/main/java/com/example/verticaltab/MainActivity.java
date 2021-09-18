package com.example.verticaltab;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.OvershootInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;


/**
 * @author wenhao
 *
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    String tag = "home";
    ConstraintLayout i1, i2, i3;
    ImageView i1Img,i2Img,i3Img;
    TextView t1, t2, t3;
    RelativeLayout currentActive;
    int prevTop = 0;

    // test
    Button toggle, component;
    Boolean stateButton = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        toggle = findViewById(R.id.toggle);
        toggle.setOnClickListener(v->{
            if (stateButton) {
                toggle.setText("hide");
                showIcon();
            } else {
                hideIcon();
                toggle.setText("show");
            }
            stateButton = !stateButton;
        });
        component = findViewById(R.id.component);
        currentActive = findViewById(R.id.active);
        i1Img = findViewById(R.id.i1_img);
        i2Img = findViewById(R.id.i2_img);
        i3Img = findViewById(R.id.i3_img);

        t1 = findViewById(R.id.i1_text);
        t2 = findViewById(R.id.i2_text);
        t3 = findViewById(R.id.i3_text);

        i1 = findViewById(R.id.i1);
        i2 = findViewById(R.id.i2);
        i3 = findViewById(R.id.i3);

        i1.setOnClickListener(this);
        i2.setOnClickListener(this);
        i3.setOnClickListener(this);
        component.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        changeTab(v.getId());
    }

    public void changeTab (int id) {
        if (id == R.id.i1 || id == 0) {
            i1Img.setActivated(true);
            activeAnimate(currentActive, i1.getTop());
            prevTop = i1.getTop();
        } else if (id == R.id.i2 || id == 1) {
            i2Img.setActivated(true);
            activeAnimate(currentActive, i2.getTop());
            prevTop = i2.getTop();
        } else if (id == R.id.i3 || id == 2) {
            i3Img.setActivated(true);
            activeAnimate(currentActive, i3.getTop());
            prevTop = i3.getTop();
        } else if (id == R.id.component) {
            Intent intent = new Intent(MainActivity.this, DetailActivity.class);
            startActivity(intent);
        }
    }

    // 菜单滑动
    private void activeAnimate(View view, int end) {
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
        Log.d(tag, "动画-> 起点=" + prevTop + "-" + "终点=" + end);
//        ani.setInterpolator(new OvershootInterpolator(1));
        ani.setDuration(100);
//        ani.setFillEnabled(true);
        ani.setFillAfter(true);
        view.startAnimation(ani);
        ani.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                Log.d(tag, "begin");
            }

            @Override
            public void onAnimationEnd(Animation animation) {
//                Log.d(tag, "finish");
//                view.clearAnimation();
                Log.d(tag, "animation" + animation);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                Log.d(tag, "repeat");
            }
        });
    }

    private void activeAllAnimate (View view, int start, int end) {
        view.setVisibility(View.VISIBLE);
        TranslateAnimation ani = new TranslateAnimation(0,0, start, end);
        ani.setDuration(100);
        ani.setFillAfter(true);
        view.startAnimation(ani);
        ani.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                Log.d(tag, "begin");
            }

            @Override
            public void onAnimationEnd(Animation animation) {
//                Log.d(tag, "finish");
//                view.clearAnimation();
                Log.d(tag, "animation" + animation);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                Log.d(tag, "repeat");
            }
        });
    }

    public void showIcon () {
        if (t1.getVisibility() != View.VISIBLE) {
            t1.setVisibility(View.VISIBLE);
            t2.setVisibility(View.VISIBLE);
            t3.setVisibility(View.VISIBLE);
            activeAllAnimate(i1Img, 10, 0);
            activeAllAnimate(i2Img, 10, 0);
            activeAllAnimate(i3Img, 10, 0);
        }
    }

    public void hideIcon () {
        if (t1.getVisibility() == View.VISIBLE) {
            t1.setVisibility(View.GONE);
            t2.setVisibility(View.GONE);
            t3.setVisibility(View.GONE);
            activeAllAnimate(i1Img, 0, 10);
            activeAllAnimate(i2Img, 0, 10);
            activeAllAnimate(i3Img, 0, 10);
        }
    }
}
