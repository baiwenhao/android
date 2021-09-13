package com.example.android_demo;
import android.os.Bundle;
import android.os.PersistableBundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    private List<Fruit> fruitList = new ArrayList<>();
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initFruits();

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setNestedScrollingEnabled(false);
        // 当我们确定Item的改变不会影响RecyclerView的宽高的时候可以设置setHasFixedSize(true)，
        // 并通过Adapter的增删改插方法去刷新RecyclerView，而不是通过notifyDataSetChanged()
        // 其实可以直接设置为true，当需要改变宽高的时候就用notifyDataSetChanged() 去整体刷新一下

        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        layoutManager.setOrientation(StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

//        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
//        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
//        recyclerView.setLayoutManager(layoutManager);

        FruitAdapter adapter = new FruitAdapter(fruitList);
        recyclerView.setAdapter(adapter);
    }

    private void initFruits(){
        Fruit apple = new Fruit("Apple",R.drawable.apple_pic);
        fruitList.add(apple);
        Fruit banana = new Fruit("Banana",R.drawable.banana_pic);
        fruitList.add(banana);
        Fruit orange = new Fruit("Orange",R.drawable.orange_pic);
        fruitList.add(orange);
        Fruit watermelon = new Fruit("Watermelon",R.drawable.watermelon_pic);
        fruitList.add(watermelon);
        Fruit pear = new Fruit("Pear",R.drawable.pear_pic);
        fruitList.add(pear);
        Fruit grape = new Fruit("Grape",R.drawable.grape_pic);
        fruitList.add(grape);
        Fruit pineapple = new Fruit("Pineapple",R.drawable.pineapple_pic);
        fruitList.add(pineapple);
        Fruit strawberry = new Fruit("Strawberry",R.drawable.strawberry_pic);
        fruitList.add(strawberry);
        Fruit cherry = new Fruit("Cherry",R.drawable.cherry_pic);
        fruitList.add(cherry);
        Fruit mango = new Fruit("Mango",R.drawable.mango_pic);
        fruitList.add(mango);
    }
}
