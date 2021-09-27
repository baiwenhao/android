package com.example.demo;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * @author wenhao
 */
public class Sidebar extends RelativeLayout implements AdapterView.OnItemClickListener,View.OnClickListener {
    ListView listView;
    Context context;
    SidebarAdapter adapter;
    RelativeLayout config,active,box;
    LinearLayout bg;
    int prevTop = 0;
    String model;
    String[] items;
    String tag = "home";

    public Sidebar(Context context) {
        super(context);
    }

    public Sidebar(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.Sidebar);
        String _model = a.getString(R.styleable.Sidebar_model);
        String _items = a.getString(R.styleable.Sidebar_items);
        if (_items != null) {
            items = _items.split(",");
        }
        if (_model != null) {
            model = _model;
        }
        a.recycle();
        init(context);
    }

    public interface OnItemSelectListener{
        void onItemSelect(int index, String indexString);
    }
    private OnItemSelectListener listener;
    public void setOnItemSelectListener(OnItemSelectListener listener2) {
        listener = listener2;
    }

    private void init(Context context) {
        this.context = context;
        View view = LayoutInflater.from(context).inflate(R.layout.sidebar, this, true);
        box = findViewById(R.id.box);
        listView = view.findViewById(R.id.list_view);
        active = view.findViewById(R.id.active);
        config = view.findViewById(R.id.config);
        bg = view.findViewById(R.id.bg);
        config.setOnClickListener(this);
        activeConfig();
        if (items != null) {
            setItem(items);
        }
        if (model != null) {
            setModel(model);
        }
    }

    @Override
    public void onClick(View v) {
        if (listener != null) {
            TextView textView = v.findViewById(R.id.icon_text);
            listener.onItemSelect(0, (String) textView.getText());
        }
        activeConfig();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (listener != null) {
            position+=1;
            TextView textView = view.findViewById(R.id.icon_text);
            listener.onItemSelect(position, (String) textView.getText());
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
        Log.d(tag, "初始化=" + model);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
        animateActive(0);
    }

    public void setModel (String model) {
        GradientDrawable drawable =(GradientDrawable) bg
                .getBackground()
                .mutate();

        if (model == "light") {
            drawable.setColor(Integer.parseInt("#fff"));
        } else if (model == "dark") {
            drawable.setColor(Integer.parseInt("#000"));
        }
    }

    public void setActivePosition (int position, Boolean state) {
        if (position < listView.getCount()) {
            animateActive(position * 140);
        }
    }

    public void setDisabledItem (int ...list) {
        int count = listView.getCount();
        if (count > 0) {
            for (int n = 0; n < count; n++) {
                View view = listView.getChildAt(n);
                view.findViewById(R.id.icon_text).setEnabled(true);
                view.findViewById(R.id.icon_img).setAlpha(1);
            }
        } else {
            return;
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

    public void clear () {
        listView.setAdapter(null);
        activeConfig();
//        listAdapter.clear();
//        listAdapter.notifyDataSetChanged() ;
    }

    public void setProximity (Boolean state) {
        int all = listView.getCount();
        int i = 0;
        while (i < all) {
            View view = listView.getChildAt(i);
            animateAlpha(view.findViewById(R.id.icon_text), state);
//            view.findViewById(R.id.icon_text).setVisibility(!state ? View.GONE : View.VISIBLE);
            if (!state) {
                animateProximity(view.findViewById(R.id.icon_img), 0, 12, true);
            } else {
                animateProximity(view.findViewById(R.id.icon_img), 12, 0, false);
            }
            i++;
        }

        if (!state) {
            animateProximity(config.findViewById(R.id.icon_img), 0, 12, true);
        } else {
            animateProximity(config.findViewById(R.id.icon_img), 12, 0, false);
        }
        box.setSelected(state);
        config.findViewById(R.id.icon_text).setVisibility(!state ? View.GONE : View.VISIBLE);
    }

    private void animateAlpha(View view, Boolean state) {
        if (state) {
            AlphaAnimation alphaAnimation = new AlphaAnimation(0,1);
            alphaAnimation.setDuration(200);
            alphaAnimation.setFillAfter(true);
            view.startAnimation(alphaAnimation);
        } else {
            AlphaAnimation alphaAnimation = new AlphaAnimation(1,0);
            alphaAnimation.setDuration(200);
            alphaAnimation.setFillAfter(true);
            view.startAnimation(alphaAnimation);
        }
    }

    private void animateProximity(View view, int start, int end, Boolean state) {
        AnimationSet animationSet = new AnimationSet(true);

        if (state) {
            ScaleAnimation scaleAnimation = new ScaleAnimation(1,1.1f,1,1.1f, 50, 50);
            animationSet.addAnimation(scaleAnimation);
        } else {
            ScaleAnimation scaleAnimation = new ScaleAnimation(1.1f,1,1.1f,1, 50, 50);
            animationSet.addAnimation(scaleAnimation);
        }

        TranslateAnimation translate = new TranslateAnimation(0,0, start, end);
        animationSet.addAnimation(translate);

        animationSet.setDuration(200);
        animationSet.setFillAfter(true);
        view.startAnimation(animationSet);
    }

    private void animateActive(int end) {
        TranslateAnimation ani = new TranslateAnimation(0,0,prevTop,end);
        ani.setDuration(100);
        ani.setFillAfter(true);
        active.startAnimation(ani);
        prevTop = end;
    }

    private void activeConfig () {
        TextView textView = config.findViewById(R.id.icon_text);
        animateActive(488);
    }
}
