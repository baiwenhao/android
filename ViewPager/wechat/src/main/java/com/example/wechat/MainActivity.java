package com.example.wechat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    ViewPager2 viewPager;
    private LinearLayout wx,contact,discover,my;
    private ImageView wxi,contacti,discoveri,myi,current;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        init();
        initMenu();
    }

    private void initMenu() {
        wx = findViewById(R.id.wx);
        contact = findViewById(R.id.contact);
        discover = findViewById(R.id.discover);
        my = findViewById(R.id.my);

        wx.setOnClickListener(this);
        contact.setOnClickListener(this);
        discover.setOnClickListener(this);
        my.setOnClickListener(this);

        wxi = findViewById(R.id.wx_ico);
        contacti = findViewById(R.id.contact_ico);
        discoveri = findViewById(R.id.discover_ico);
        myi = findViewById(R.id.my_ico);

        wxi.setSelected(true);
        current = wxi;
    }

    public void init () {
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(MyFragment.newInstance("我的微信"));
        fragments.add(MyFragment.newInstance("我的发现"));
        fragments.add(MyFragment.newInstance("我的日志"));
        fragments.add(MyFragment.newInstance("我的设置"));
        viewPager = findViewById(R.id.viewpage);
        MyAdapter myFragmentAdapter = new MyAdapter(getSupportFragmentManager(), getLifecycle(), fragments);
        viewPager.setAdapter(myFragmentAdapter);
        // 滑动viewpager的监听
        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }

            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                changeTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                super.onPageScrollStateChanged(state);
            }
        });
    }

    @Override
    public void onClick(View v) {
        changeTab(v.getId());
    }

    public void changeTab (int position) {
        Log.d("home", "position=" + position);
        current.setSelected(false);
        switch (position) {
            case R.id.wx:
                viewPager.setCurrentItem(0);
            case 0:
                wxi.setSelected(true);
                current = wxi;
                break;
            case R.id.contact:
                viewPager.setCurrentItem(1);
            case 1:
                contacti.setSelected(true);
                current = contacti;
                break;
            case R.id.discover:
                viewPager.setCurrentItem(2);
            case 2:
                discoveri.setSelected(true);
                current = discoveri;
                break;
            case R.id.my:
                viewPager.setCurrentItem(3);
            case 3:
                myi.setSelected(true);
                current = myi;
                break;
        }
    }
}