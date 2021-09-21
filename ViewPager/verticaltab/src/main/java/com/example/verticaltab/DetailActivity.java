package com.example.verticaltab;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.Button;
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
    Button toggle, component, color;
    Boolean stateButton = false;

    int prevTop = 0;
    ListView listView;
    RelativeLayout active, config;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        getSupportActionBar().hide();
        init();
        buildItem();
    }

    private void init() {
        listView = findViewById(R.id.list_view);
        active = findViewById(R.id.active);
        config = findViewById(R.id.config);
        toggle = findViewById(R.id.toggle);
        component = findViewById(R.id.component);
        color = findViewById(R.id.color);

        toggle.setOnClickListener(v-> {
            toggleTextView(stateButton);
            stateButton = !stateButton;
        });

        config.setOnClickListener(v->{
            animateItem(v.getTop());
            prevTop = v.getTop();
        });

        color.setOnClickListener(v-> {
            Random rnd = new Random();
            int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
            Log.d(tag, "color:" + color);
            setColor(color);
        });
        component.setOnClickListener(v->{
            Intent intent = new Intent(DetailActivity.this, MainActivity.class);
            startActivity(intent);
        });
    }

    public void buildItem () {
        // method 1
//        String[] data = {"Home","list","detail","input"};
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
//                this,
//                android.R.layout.simple_list_item_1,
//                data
//        );

        // method 2 build data
        List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
        Map<String,Object> map = new HashMap<>(2);
        map.put("text", "home");
        map.put("icon", R.drawable.icon_start_selector);
        list.add(map);

        map = new HashMap<>(2);
        map.put("text", "list");
        map.put("icon", R.drawable.icon_start_selector);
        list.add(map);

        map = new HashMap<>(2);
        map.put("text", "detail");
        map.put("icon", R.drawable.icon_start_selector);
        list.add(map);

//        SimpleAdapter adapter = new SimpleAdapter(
//                this,
//                list,
//                R.layout.item_custom,
//                new String[]{"text","icon"},
//                new int[]{R.id.icon_text,R.id.icon_img}
//        );

        // method 3
        ItemAdapter adapter = new ItemAdapter(this);
        adapter.setList(list);

        // 关联适配器
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

    // common
    public void setMain () {

    }

    public void setColor (int color) {
        GradientDrawable drawable =(GradientDrawable) active.findViewById(R.id.light)
                .getBackground()
                .mutate();
        active.findViewById(R.id.shadow).setBackgroundColor(color);
        drawable.setColor(color);
    }

    public void setDisabled () {

    }

    public void toggleTextView (Boolean state) {
        int all = listView.getCount();
        int i = 0;
        while (i < all) {
            View view = listView.getChildAt(i);
            view.findViewById(R.id.icon_text).setVisibility(state == false ? View.GONE : View.VISIBLE);
            if (state == false) {
                animateIcon(view.findViewById(R.id.icon_img), 0, 10);
            } else {
                animateIcon(view.findViewById(R.id.icon_img), 10, 0);
            }
            i++;
        }
        if (state == false) {
            animateIcon(config.findViewById(R.id.icon_img), 0, 10);
        } else {
            animateIcon(config.findViewById(R.id.icon_img), 10, 0);
        }
        config.findViewById(R.id.icon_text).setVisibility(state == false ? View.GONE : View.VISIBLE);
    }
}

// 普通按钮，布局绑定事件
// View.OnClickListener,
//public void onClick(View v) {}

// 由于 inflate 和 findViewById 为主要耗时方法，因此要做优化
