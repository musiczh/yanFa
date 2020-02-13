package com.example.yanfa.iApiService;

import com.example.yanfa.bean.BaseBean;

import retrofit2.Call;
import retrofit2.http.GET;

import static com.example.yanfa.util.URLStaticQuality.NOTICE;

public interface NoticeApiService {

    @GET(NOTICE)
    Call<BaseBean<String>> getNotice();
}
