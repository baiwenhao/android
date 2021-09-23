package com.example.demo;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wenhao
 */
public class Sibebar extends RelativeLayout implements AdapterView.OnItemClickListener {
    SidebarAdapter adapter;
    RelativeLayout config,active;
    ListView listView;
    LinearLayout bg;
    Context context;
    int prevTop = 0;

    public Sibebar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        context = context;
        View view = LayoutInflater.from(context).inflate(R.layout.sidebar, this);
        listView = view.findViewById(R.id.list_view);
        active = view.findViewById(R.id.active);
        config = view.findViewById(R.id.config);
        bg = view.findViewById(R.id.bg);
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
                animateIcon(view.findViewById(R.id.icon_img), 0, 12);
            } else {
                animateIcon(view.findViewById(R.id.icon_img), 12, 0);
            }
            i++;
        }
        if (!state) {
            animateIcon(config.findViewById(R.id.icon_img), 0, 12);
        } else {
            animateIcon(config.findViewById(R.id.icon_img), 12, 0);
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
        adapter = new SidebarAdapter(context);
        adapter.setList(list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
        active.setVisibility(View.VISIBLE);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
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
