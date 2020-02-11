package com.example.yanfa.iApiService;

import com.example.yanfa.bean.RegLoginInfo;
import com.example.yanfa.bean.Result;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

import static com.example.yanfa.util.URLStaticQuality.LOGIN_DO;

public interface LoginDoApiService {

    @POST(LOGIN_DO)
    Call<Result> Login(@Body RegLoginInfo regLoginInfo);
}
