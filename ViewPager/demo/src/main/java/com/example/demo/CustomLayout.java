package com.example.demo;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class CustomLayout extends RelativeLayout {
    TextView textView;
    public CustomLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.custom_layout, this);
        textView = view.findViewById(R.id.text_view);
        textView.setText("大家好");
    }

    public void setTitle (String title) {
        textView.setText(title);
    }
}
