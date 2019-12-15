package com.example.yanfa.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.example.yanfa.MainActivity;
import com.example.yanfa.R;

public class LaunchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);

        ImageView imageView = findViewById(R.id.imageView_launch);
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.launch_img);
        imageView.setAnimation(animation);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                start();
                LaunchActivity.this.finish();

            }
        }).start();
    }

    private void start() {
        Intent intent = new Intent(LaunchActivity.this, MainActivity.class);
        startActivity(intent);
    }
}
