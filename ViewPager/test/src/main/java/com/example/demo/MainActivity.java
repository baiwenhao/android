package com.example.demo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.widget.LinearLayout;

/**
 * @author wenhao
 */
public class MainActivity extends AppCompatActivity {
    LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 定义ViePager2
        // 为ViewPager构建Adapter

        ViewPager2 viewPager = findViewById(R.id.viewPager);
        MyAdapter viewPagerAdapter = new MyAdapter();
        viewPager.setAdapter(viewPagerAdapter);

//        linearLayout = findViewById(R.id.btn);
//        linearLayout.setOnClickListener((v)-> {
//            linearLayout.setActivated(true);
//        });

        // 动态设置背景色
//        viewPager.setBackgroundColor(Color.parseColor("#FF00FF"));
//        viewPager.setBackgroundResource(R.drawable.main_button);
    }
}