package com.example.animate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.google.gson.Gson;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        findViewById(R.id.button).setOnClickListener(v-> {
            Bean bean=new Bean();
            bean.setFenceId("0003883271");
            Gson gson=new Gson();
            String s=gson.toJson(bean);

            // 0003883271
            //  {"launchType":"3","packageName":"com.maezia.ezia.fueling","action":null,"pageName":null,"uri":"fueling://detail","params":{"fenceId":"0003883271"}}
            String url = "fueling://detail";
            Intent intent=new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            intent.putExtra("data", s);
            startActivity(intent);
            finish();
        });

    }
}