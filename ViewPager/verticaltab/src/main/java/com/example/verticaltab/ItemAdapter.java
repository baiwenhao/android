package com.example.verticaltab;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.time.Instant;
import java.util.List;
import java.util.Map;

public class ItemAdapter extends BaseAdapter {
    List<Map<String,Object>> list ;
    LayoutInflater inflater;

    public ItemAdapter(Context context) {
        this.inflater=LayoutInflater.from(context);
    }

    public void setList(List<Map<String, Object>> list) {
        this.list=list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // 反射行布局
        View view = inflater.inflate(R.layout.item_custom,null);

        // 获取各个控件
        ImageView icon = view.findViewById(R.id.icon_img);
        TextView text = view.findViewById(R.id.icon_text);

        // 给各控件赋值
        Map map = list.get(position);
//        icon.setBackground((Drawable) map.get("icon"));
        text.setText((String) map.get("text"));

        return view;
    }
}
