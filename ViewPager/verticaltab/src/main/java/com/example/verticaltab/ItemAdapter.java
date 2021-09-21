package com.example.verticaltab;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

public class ItemAdapter extends BaseAdapter {
    String tag = "home";
    List<Map<String,Object>> list ;
    LayoutInflater inflater;
    Context context;

    public ItemAdapter(Context context) {
        this.inflater=LayoutInflater.from(context);
        this.context = context;
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
        ViewHolder holder;

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_custom, null);
            holder = new ViewHolder();
            holder.icon = convertView.findViewById(R.id.icon_img);
            holder.text = convertView.findViewById(R.id.icon_text);
            convertView.setTag(holder);
        } else {
            holder =(ViewHolder) convertView.getTag();
        }
//        context.getResources().getDrawable(map.get("icon")) // 获取类型
        Map map = list.get(position);
        holder.icon.setImageResource((Integer) map.get("icon")); // Drawable error
        holder.text.setText((String) map.get("text"));

        return convertView;
    }

    public class ViewHolder {
        ImageView icon;
        TextView text;
    }
}
