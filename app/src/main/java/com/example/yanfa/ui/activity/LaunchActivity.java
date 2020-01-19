package com.example.yanfa.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.example.yanfa.MainActivity;
import com.example.yanfa.R;

/**
 * 启动页activity;等待
 */
public class LaunchActivity extends AppCompatActivity {
    //等待跳转的时间
    private static int WAIT_TIME = 1500; 

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            LaunchActivity.this.getWindow().getDecorView().setSystemUiVisibility( View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
        setContentView(R.layout.activity_launch);

        ImageView imageView = findViewById(R.id.imageView_launch);
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.launch_img);
        imageView.setAnimation(animation);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(WAIT_TIME);
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
