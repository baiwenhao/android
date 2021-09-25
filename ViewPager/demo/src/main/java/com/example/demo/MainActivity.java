package com.example.demo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.Objects;
import java.util.Random;

/**
 * @author wenhao
 * 自定义控件分类：
 * 自绘式自定义控件
 * 继承式自定义控件
 * 组合控件
 */

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Sidebar sidebar;
    String tag = "home";

    Button btn1,btn2,btn3,btn4,btn5;
    Boolean state=false,state2=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Objects.requireNonNull(getSupportActionBar()).hide();

        ViewPager2 viewPager = findViewById(R.id.viewPager);
        MyAdapter viewPagerAdapter = new MyAdapter();
        viewPager.setAdapter(viewPagerAdapter);

        // component
        sidebar = findViewById(R.id.sidebar);
        Log.d(tag, "mainactivity");
//        sidebar.setItem("home","list","list");
//        sidebar.setActivePosition(1);
        sidebar.setOnItemSelectListener(new Sidebar.OnItemSelectListener() {
            @Override
            public void onItemSelect(int position, String text) {
                btn5.setText(text);
            }
        });

        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);
        btn5 = findViewById(R.id.btn5);

        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.btn1) {
            sidebar.setProximity(state2);
            state2=!state2;
        } else if (id == R.id.btn2) {
            if (!state) {
                sidebar.setDisabledItem(0, 1);
            } else {
                sidebar.setDisabledItem();
            }
            state=!state;
        } else if (id == R.id.btn3) {
            Random rnd = new Random();
            int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
            sidebar.setActiveColor(color);
        } else if (id == R.id.btn4) {
            //
        }
    }
}

