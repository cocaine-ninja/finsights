package com.kingsmen.finsights;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.Timer;

public class Loader extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loader);

        ImageView imageView = findViewById(R.id.imageView2);
        Glide.with(this).load(R.drawable.loader).into(imageView);

        new java.util.Timer().schedule(
                new java.util.TimerTask() {
                    @Override
                    public void run() {
                        // your code here
                        Intent intent = new Intent(getApplicationContext(), SignUp.class);
                        startActivity(intent);
                    }
                },
                2000
        );

    }
}