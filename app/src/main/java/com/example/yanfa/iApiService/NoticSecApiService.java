package com.example.yanfa.iApiService;

import com.example.yanfa.bean.BaseBean;
import com.example.yanfa.bean.NoticBean;

import retrofit2.Call;
import retrofit2.http.GET;

import static com.example.yanfa.util.URLStaticQuality.NOTICE;

public interface NoticSecApiService {
    @GET(NOTICE)
    Call<NoticBean> getNoticeSec();
}
