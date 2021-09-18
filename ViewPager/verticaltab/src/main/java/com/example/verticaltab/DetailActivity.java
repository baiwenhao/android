package com.example.verticaltab;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DetailActivity extends AppCompatActivity implements View.OnClickListener {
    String tag = "home";
    ListView listView;
    RelativeLayout active;
    int prevTop = 0;
    Button toggle, component;
    Boolean stateButton = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        getSupportActionBar().hide();
        init();
        setMain();
    }

    private void init() {
        listView = findViewById(R.id.list_view);
        active = findViewById(R.id.active);
        toggle = findViewById(R.id.toggle);
        component = findViewById(R.id.component);

        toggle.setOnClickListener(this);
        component.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.component) {
            Intent intent = new Intent(DetailActivity.this, MainActivity.class);
            startActivity(intent);
        } else if (id == R.id.toggle) {
            if (stateButton == false) {

            } else {

            }
            stateButton = !stateButton;
        }
    }

    // 菜单滑动
    private void activeAnimate(View view, int end) {
        view.setVisibility(View.VISIBLE);
        TranslateAnimation ani = new TranslateAnimation(0,0,prevTop,end);
        ani.setDuration(100);
        ani.setFillAfter(true);
        view.startAnimation(ani);
    }

    // 对外提供的方法
    public void setDisabled () { // 禁用启动菜单

    }

    public void setMain () {
        // method 1
//        String[] data = {"Home","list","detail","input"};
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
//                this,
//                android.R.layout.simple_list_item_1,
//                data
//        );

        // method 2
        List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("text", "home");
//        map.put("icon", R.drawable.icon_start_style);
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("text", "detail");
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

        listView.setAdapter(adapter);
    }

    public void callBack () { // 监听点击事件

    }

    public void setActive () { // 高亮菜单

    }

    public void setColor () { // 氛围灯

    }

    // 国际化

}