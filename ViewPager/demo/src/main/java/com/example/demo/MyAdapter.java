package com.example.demo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewPageViewHolder> {
    private List<String> titles = new ArrayList<>();
    private List<Integer> colors = new ArrayList<>();

    // 适配器初始化方法
    public MyAdapter() {
        titles.add("吃鸡");
        titles.add("王者");
        titles.add("三国");

        colors.add(R.color.white);
        colors.add(R.color.teal_700);
        colors.add(R.color.purple_700);
    }

    @NonNull
    @Override
    public ViewPageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewPageViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pager, parent, false));
    }

    // 数据的绑定
    @Override
    public void onBindViewHolder(@NonNull ViewPageViewHolder holder, int position) {
        holder.textView.setText(titles.get(position));
        holder.item.setBackgroundResource(colors.get(position));
    }

    @Override
    public int getItemCount() {
        return 3;
    }

    // 定义页面的适配器
    static class ViewPageViewHolder extends RecyclerView.ViewHolder{

        TextView textView;
        RelativeLayout item;
        public ViewPageViewHolder(@NonNull View itemView) {
            super(itemView);

            item = itemView.findViewById(R.id.item);
            textView = itemView.findViewById(R.id.title);
        }
    }
}
