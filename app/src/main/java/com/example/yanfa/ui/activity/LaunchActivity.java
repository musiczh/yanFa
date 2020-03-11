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
import com.example.yanfa.bean.BaseBean;
import com.example.yanfa.iApiService.NoticeApiService;
import com.example.yanfa.util.AddCookiesInterceptor;
import com.example.yanfa.util.NetworkUtil;
import com.example.yanfa.util.ReceivedCookiesInterceptor;


import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.yanfa.util.URLStaticQuality.BASE_URL;

/**
 * 启动页activity;等待
 */
public class LaunchActivity extends AppCompatActivity {
    //等待跳转的时间
    private static int WAIT_TIME = 1500;
    //是否已经登录了
    private  boolean ifLogin = false;

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
                if (NetworkUtil.isNetworkAvailable(LaunchActivity.this))
                haveLogin();
                else{
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(LaunchActivity.this,"网络开了点小差",Toast.LENGTH_SHORT).show();
                        }
                    });
                }
                try {
                    Thread.sleep(1000);
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
        intent.putExtra("ifLogin",ifLogin);
        startActivity(intent);
        overridePendingTransition(R.anim.activity_start_anim,R.anim.activity_finish_anim);
    }

    //判断是否已经登录了；或者是否cookie已经过期
    private void haveLogin(){
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
        httpClientBuilder.connectTimeout(5, TimeUnit.SECONDS);

        httpClientBuilder.addInterceptor(new ReceivedCookiesInterceptor(this));
        httpClientBuilder.addInterceptor(new AddCookiesInterceptor(this));


        new Retrofit.Builder()
                .client(httpClientBuilder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(BASE_URL)
                .build()
                .create(NoticeApiService.class)
                .getNotice()
                .enqueue(new Callback<BaseBean<String>>() {
                    @Override
                    public void onResponse(Call<BaseBean<String>> call, Response<BaseBean<String>> response) {

                        if (response.body()!=null){
                            ifLogin = response.body().getCode() == 200;
                        }
                    }

                    @Override
                    public void onFailure(Call<BaseBean<String>> call, Throwable t) {
                        Toast.makeText(LaunchActivity.this,"网络似乎出了点小问题哦",Toast.LENGTH_SHORT).show();
                    }
                });

    }

    @Override
    public void finish() {
        super.finish();

    }
}
