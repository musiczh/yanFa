package com.example.yanfa.iApiService;

import com.example.yanfa.bean.Result;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

import static com.example.yanfa.util.URLStaticQuality.GET_REGISTER_CODE;
import static com.example.yanfa.util.URLStaticQuality.IF_ENROLL_DO;

public interface IfEnrollApiService {

    @GET(IF_ENROLL_DO)
    Call<Result> ifEnroll(@Query("phoneNum")String phoneNum);
}
