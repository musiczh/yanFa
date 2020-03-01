package com.example.yanfa.util;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import java.io.IOException;
import java.util.HashSet;

import okhttp3.Interceptor;

import static android.content.Context.MODE_PRIVATE;

public class ReceivedCookiesInterceptor implements Interceptor {

    private Context mContext;


    public ReceivedCookiesInterceptor( Context context){
        mContext  = context;
    }

    @Override
    public okhttp3.Response intercept(Chain chain) throws IOException {
        okhttp3.Response originalResponse = chain.proceed(chain.request());

        if (!originalResponse.headers("Set-Cookie").isEmpty()) {


            HashSet<String> cookies = new HashSet<>(originalResponse.headers("Set-Cookie"));
            SharedPreferences.Editor config = mContext.getSharedPreferences("config", MODE_PRIVATE)
                    .edit();
            config.putStringSet("cookie", cookies);
            config.apply();
        }

        return originalResponse;
    }
}
