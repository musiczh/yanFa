package com.example.yanfa.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;

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
        setContentView(R.layout.activity_launch);

        ImageView imageView = findViewById(R.id.imageView_launch);
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.launch_img);
        imageView.setAnimation(animation);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        ImageView imageView1 = findViewById(R.id.imageView_launch_yanfa);
        ValueAnimator valueAnimator = ObjectAnimator.ofFloat(imageView1,"translationX",dm.widthPixels/2,0);
        valueAnimator.setDuration(800);
        valueAnimator.start();

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
