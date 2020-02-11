package com.example.yanfa.iApiService;

import com.example.yanfa.bean.Result;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

import static com.example.yanfa.util.URLStaticQuality.GET_REGISTER_CODE;

public interface RegisterGetCodeApiService {

    @GET(GET_REGISTER_CODE)
    Call<Result> getRegisterCode(@Query("phoneNum")String phoneNum);
}
