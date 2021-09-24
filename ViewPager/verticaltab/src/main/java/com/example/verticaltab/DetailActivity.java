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
import java.util.Objects;
import java.util.Random;

/**
 * @author wenhao
 */
public class DetailActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    String tag = "home";
    Button toggle, color, disable;
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
        Objects.requireNonNull(getSupportActionBar()).hide();

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
            if (!disabledState) {
                setDisabledItem(0);
            } else {
                setDisabledItem();
            }
            disable.setText(!disabledState ? "enabled first" : "disabled first");
            disabledState = !disabledState;
        });
    }

    public void setDisabledItem (int ...list) {
        int count = listView.getCount();
        for (int n = 0; n < count; n++) {
            View view = listView.getChildAt(n);
            view.findViewById(R.id.icon_text).setEnabled(true);
            view.findViewById(R.id.icon_img).setAlpha(1);
        }
        if (list.length >= 1) {
            for (int j : list) {
                View view=listView.getChildAt(j);
                view.findViewById(R.id.icon_text).setEnabled(false);
                view.findViewById(R.id.icon_img).setAlpha(0.4f);
            }
        }
        adapter.setDisabledItem(list);
    }

    public void setActivityColor (int color) {
        GradientDrawable drawable =(GradientDrawable) active.findViewById(R.id.light)
                .getBackground()
                .mutate();
        if (drawable != null) { drawable.setColor(color); }
    }

    public void setProximity (Boolean state) {
        int all = listView.getCount();
        int i = 0;
        while (i < all) {
            View view = listView.getChildAt(i);
            view.findViewById(R.id.icon_text).setVisibility(!state ? View.GONE : View.VISIBLE);
            if (!state) {
                animateIcon(view.findViewById(R.id.icon_img), 0, 10);
            } else {
                animateIcon(view.findViewById(R.id.icon_img), 10, 0);
            }
            i++;
        }
        if (!state) {
            animateIcon(config.findViewById(R.id.icon_img), 0, 10);
        } else {
            animateIcon(config.findViewById(R.id.icon_img), 10, 0);
        }
        config.findViewById(R.id.icon_text).setVisibility(!state ? View.GONE : View.VISIBLE);
    }

    public void setMain (String... arr) {
        List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
        Map<String,Object> map;
        for (String s : arr) {
            if ("home".equals(s)) {
                map=new HashMap<>(2);
                map.put("text", "home");
                map.put("icon", R.drawable.icon_start_selector);
                list.add(map);
            } else if ("list".equals(s)) {
                map=new HashMap<>(2);
                map.put("text", "list");
                map.put("icon", R.drawable.icon_start_selector);
                list.add(map);
            } else if ("detail".equals(s)) {
                map=new HashMap<>(2);
                map.put("text", "detail");
                map.put("icon", R.drawable.icon_start_selector);
                list.add(map);
            } else if ("input".equals(s)) {
                map=new HashMap<>(2);
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
    public void onItemClick (AdapterView<?> parent, View view, int position, long id) {
        animateItem(view.getTop());
        prevTop = view.getTop();
    }

    private void animateIcon (View view, int start, int end) {
        view.setVisibility(View.VISIBLE);
        TranslateAnimation ani = new TranslateAnimation(0,0, start, end);
        ani.setDuration(100);
        ani.setFillAfter(true);
        view.startAnimation(ani);
    }

    private void animateItem (int end) {
        TranslateAnimation ani = new TranslateAnimation(0,0,prevTop,end);
        ani.setDuration(100);
        ani.setFillAfter(true);
        active.startAnimation(ani);
    }
}