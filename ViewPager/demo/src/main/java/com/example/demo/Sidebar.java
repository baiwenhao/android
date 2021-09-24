package com.example.demo;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;
import android.util.Log;
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
public class Sidebar extends RelativeLayout implements AdapterView.OnItemClickListener,View.OnClickListener {
    SidebarAdapter adapter;
    RelativeLayout config,active;
    ListView listView;
    LinearLayout bg;
    Context context;
    int prevTop = 0;
    String tag = "sidebar";

    // 通过Java代码创建控件时调用
    public Sidebar(Context context) {
        super(context);
    }

    // 添加到布局文件后，系统调用
    public Sidebar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }
    public interface OnItemSelectListener{
        void onItemSelect(int index, String indexString);
    }
    private OnItemSelectListener listener;
    public void setOnItemSelectListener(OnItemSelectListener listener2) {
        this.listener = listener2;
    }

    private void init(Context context) {
        this.context = context;
        View view = LayoutInflater.from(context).inflate(R.layout.sidebar, this, true);
        listView = view.findViewById(R.id.list_view);
        active = view.findViewById(R.id.active);
        config = view.findViewById(R.id.config);
        bg = view.findViewById(R.id.bg);
        config.setOnClickListener(this);
        activeConfig();
    }

    @Override
    public void onClick(View v) {
        activeConfig();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (listener != null) {
            listener.onItemSelect(1, "1");
        }
        animateActive(view.getTop());
    }

    public void setItem (String... arr) {
        List<Map<String,Object>> list = new ArrayList<>();
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
        animateActive(0);
    }

    public void setActivePosition (int position) {
        if (position < listView.getCount()) {
            animateActive(position * 140);
        }
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

    public void setActiveColor (int color) {
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
                animateProximity(view.findViewById(R.id.icon_img), 0, 12);
            } else {
                animateProximity(view.findViewById(R.id.icon_img), 12, 0);
            }
            i++;
        }
        if (!state) {
            animateProximity(config.findViewById(R.id.icon_img), 0, 12);
        } else {
            animateProximity(config.findViewById(R.id.icon_img), 12, 0);
        }
        config.findViewById(R.id.icon_text).setVisibility(!state ? View.GONE : View.VISIBLE);
    }

    private void animateProximity(View view, int start, int end) {
        TranslateAnimation ani = new TranslateAnimation(0,0, start, end);
        ani.setDuration(100);
        ani.setFillAfter(true);
        view.startAnimation(ani);
    }

    private void animateActive(int end) {
        TranslateAnimation ani = new TranslateAnimation(0,0,prevTop,end);
        ani.setDuration(100);
        ani.setFillAfter(true);
        active.startAnimation(ani);
        prevTop = end;
    }

    private void activeConfig () {
        animateActive(488);
    }
}
