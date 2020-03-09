package com.example.yanfa.iApiService;

import com.example.yanfa.bean.NoticBean;
import com.example.yanfa.bean.PhaseBean;

import retrofit2.Call;
import retrofit2.http.GET;
import static com.example.yanfa.util.URLStaticQuality.PHASE;

public interface PhaseApiService {
    @GET(PHASE)
    Call<PhaseBean> getPhase();
}
