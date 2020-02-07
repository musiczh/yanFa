package com.example.yanfa.iApiService;

import com.example.yanfa.bean.RegLoginInfo;
import com.example.yanfa.bean.Result;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

import static com.example.yanfa.util.URLStaticQuality.REGISTER_DO;

public interface RegisterDoApiService {

    @POST(REGISTER_DO)
    Call<Result> register(@Body RegLoginInfo regLoginInfo);
}

