package com.example.demo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.widget.LinearLayout;

/**
 * @author wenhao
 * 自定义控件分类：
 * 自绘式自定义控件
 * 继承式自定义控件
 * 组合控件
 */

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewPager2 viewPager = findViewById(R.id.viewPager);
        MyAdapter viewPagerAdapter = new MyAdapter();
        viewPager.setAdapter(viewPagerAdapter);

        // 动态设置背景色
//        viewPager.setBackgroundColor(Color.parseColor("#FF00FF"));
//        viewPager.setBackgroundResource(R.drawable.main_button);
    }
}

