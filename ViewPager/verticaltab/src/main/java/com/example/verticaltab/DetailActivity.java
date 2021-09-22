package com.example.verticaltab;

import androidx.appcompat.app.AppCompatActivity;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * @author wenhao
 */
public class DetailActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    String tag = "home";
    Button toggle, color, disable, theme;
    Boolean stateButton=false, disabledState=false;

    LinearLayout bg;
    int prevTop = 0;
    ListView listView;
    RelativeLayout active, config;
    ItemAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        getSupportActionBar().hide();
        init();
        setMain("home", "list", "detail");
    }

    private void init() {
        listView = findViewById(R.id.list_view);
        active = findViewById(R.id.active);
        config = findViewById(R.id.config);
        toggle = findViewById(R.id.toggle);
        color = findViewById(R.id.color);
        disable = findViewById(R.id.disable);
        theme = findViewById(R.id.theme);
        bg = findViewById(R.id.bg);

        toggle.setOnClickListener(v->{
            setProximity(stateButton);
            stateButton = !stateButton;
        });

        config.setOnClickListener(v->{
            animateItem(v.getTop());
            prevTop = v.getTop();
        });

        color.setOnClickListener(v->{
            Random rnd = new Random();
            int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
            Log.d(tag, "color:" + color);
            setActivityColor(color);
        });

        disable.setOnClickListener(v->{
            if (disabledState == false) {
                setDisabledItem(0);
            } else {
                setDisabledItem();
            }
            disable.setText(disabledState == false ? "enabled first" : "disabled first");
            disabledState = !disabledState;
        });
        theme.setOnClickListener(v->{
            GradientDrawable drawable =(GradientDrawable) bg.getBackground().mutate();
            if (drawable != null) {
                drawable.setColor(Color.argb(255, 26, 26, 26));
            } else {
                Log.d(tag, "does not exist");
            }
        });
    }

    public void setDisabledItem(int ...list) {
        int count = listView.getCount();
        for (int n = 0; n < count; n++) {
            View view = listView.getChildAt(n);
            view.findViewById(R.id.icon_text).setEnabled(true);
            view.findViewById(R.id.icon_img).setAlpha(1);
        }
        if (list.length >= 1) {
            for (int i = 0; i < list.length; i++) {
                View view = listView.getChildAt(list[i]);
                view.findViewById(R.id.icon_text).setEnabled(false);
                view.findViewById(R.id.icon_img).setAlpha(0.4f);
            }
        }
        adapter.setDisabledItem(list);
    }

    public void setActivityColor(int color) {
        GradientDrawable drawable =(GradientDrawable) active.findViewById(R.id.light)
                .getBackground()
                .mutate();
        if (drawable != null) { drawable.setColor(color); }
    }

    public void setProximity(Boolean state) {
        int all = listView.getCount();
        int i = 0;
        while (i < all) {
            View view = listView.getChildAt(i);
            view.findViewById(R.id.icon_text).setVisibility(state == false ? View.GONE : View.VISIBLE);
            if (state == false) {
                animateIcon(view.findViewById(R.id.icon_img), 0, 12);
            } else {
                animateIcon(view.findViewById(R.id.icon_img), 12, 0);
            }
            i++;
        }
        if (state == false) {
            animateIcon(config.findViewById(R.id.icon_img), 0, 12);
        } else {
            animateIcon(config.findViewById(R.id.icon_img), 12, 0);
        }
        config.findViewById(R.id.icon_text).setVisibility(state == false ? View.GONE : View.VISIBLE);
    }

    public void setMain (String... arr) {
        List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
        Map<String,Object> map;
        for (int i = 0;i < arr.length;i++) {
            if (arr[i] == "home") {
                map = new HashMap<>(2);
                map.put("text", "home");
                map.put("icon", R.drawable.icon_start_selector);
                list.add(map);
            } else if (arr[i] == "list") {
                map = new HashMap<>(2);
                map.put("text", "list");
                map.put("icon", R.drawable.icon_start_selector);
                list.add(map);
            } else if (arr[i] == "detail") {
                map = new HashMap<>(2);
                map.put("text", "detail");
                map.put("icon", R.drawable.icon_start_selector);
                list.add(map);
            } else if (arr[i] == "input") {
                map = new HashMap<>(2);
                map.put("text", "input");
                map.put("icon", R.drawable.icon_start_selector);
                list.add(map);
            }
        }
        adapter = new ItemAdapter(this);
        adapter.setList(list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);

        active.setVisibility(View.VISIBLE);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (position == 0) {
            animateItem(view.getTop());
        } else if (position == 1) {
            animateItem(view.getTop());
        } else if (position == 2) {
            animateItem(view.getTop());
        }
        prevTop = view.getTop();
    }

    private void animateIcon (View view, int start, int end) {
        view.setVisibility(View.VISIBLE);
        TranslateAnimation ani = new TranslateAnimation(0,0, start, end);
        ani.setDuration(100);
        ani.setFillAfter(true);
        view.startAnimation(ani);
    }

    private void animateItem(int end) {
        TranslateAnimation ani = new TranslateAnimation(0,0,prevTop,end);
        ani.setDuration(100);
        ani.setFillAfter(true);
        active.startAnimation(ani);
    }
}

// 普通按钮，布局绑定事件
// View.OnClickListener,
//public void onClick(View v) {}

// 由于 inflate 和 findViewById 为主要耗时方法，因此要做优化

// 缺少点击的回调事件