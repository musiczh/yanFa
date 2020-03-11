package com.example.yanfa.model;

import android.util.Log;

import com.example.yanfa.bean.EnrollBean;
import com.example.yanfa.bean.Result;
import com.example.yanfa.contract.IEnrollContract;
import com.example.yanfa.iApiService.EnrollApiService;
import com.example.yanfa.iApiService.IfEnrollApiService;
import com.example.yanfa.util.RetrofitManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 报名界面的Model层
 */
public class EnrollModel implements IEnrollContract.IModel {
    private IEnrollContract.IPresenter mPresenter;

    public EnrollModel(IEnrollContract.IPresenter presenter){
        mPresenter = presenter;
    }


    @Override
    public void enroll(EnrollBean enrollBean) {
        RetrofitManager.getInstance().createRs(EnrollApiService.class)
                .enroll(enrollBean)
                .enqueue(new Callback<Result>() {
                    @Override
                    public void onResponse(Call<Result> call, Response<Result> response) {
                        if(response.body()!=null) {
                            if (response.body().getResult().equals("ok")) {
                                mPresenter.onSuccess(response.body().getResult());
                                Log.d("model", response.body().getResult());
                            } else {
                                mPresenter.onFailure(response.body().getResult());
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<Result> call, Throwable t) {
                        mPresenter.onFailure("报名失败");
                    }
                });

    }

    @Override
    public void goIfEnroll(String phoneStum) {
        RetrofitManager.getInstance().createRs(IfEnrollApiService.class)
                .ifEnroll(phoneStum)
                .enqueue(new Callback<Result>() {
                    @Override
                    public void onResponse(Call<Result> call, Response<Result> response) {
                        if(response.body()!=null)
                            if(response.body().getResult().equals("ok")){
                                mPresenter.ifEnroll(true);
                            }

                    }

                    @Override
                    public void onFailure(Call<Result> call, Throwable t) {
                        mPresenter.ifEnroll(false);
                    }
                });
    }
}
