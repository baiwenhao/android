package com.example.demo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 定义ViePager2
        // 为ViewPager构建Adapter

        ViewPager2 viewPager = findViewById(R.id.viewPager);
        MyAdapter viewPagerAdapter = new MyAdapter(); // 创建适配器
        viewPager.setAdapter(viewPagerAdapter);
    }
}