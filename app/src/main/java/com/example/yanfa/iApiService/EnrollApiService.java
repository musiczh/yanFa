package com.example.yanfa.iApiService;

import com.example.yanfa.bean.EnrollBean;
import com.example.yanfa.bean.Result;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

import static com.example.yanfa.util.URLStaticQuality.ENROLL_DO;

public interface EnrollApiService {

    @POST(ENROLL_DO)
    Call<Result> enroll(@Body EnrollBean enrollBean);
}
