package com.example.yanfa.iApiService;

import android.graphics.Bitmap;

import java.io.BufferedInputStream;
import java.io.InputStream;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Streaming;

import static com.example.yanfa.util.URLStaticQuality.LOGIN_IMAGE_CODE;

public interface LoginGetImageCodeApiService {

    @Streaming
    @GET(LOGIN_IMAGE_CODE)
    Call<ResponseBody> getImageCode(@Query("phoneNum")String phoneNum);
}
