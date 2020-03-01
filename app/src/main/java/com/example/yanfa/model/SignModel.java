package com.example.yanfa.model;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.yanfa.bean.RegLoginInfo;
import com.example.yanfa.bean.Result;
import com.example.yanfa.iApiService.LoginDoApiService;
import com.example.yanfa.iApiService.LoginGetImageCodeApiService;
import com.example.yanfa.iApiService.RegisterDoApiService;
import com.example.yanfa.iApiService.RegisterGetCodeApiService;
import com.example.yanfa.interfaces.LoginModelInter;
import com.example.yanfa.interfaces.ModelCallBack;
import com.example.yanfa.util.RetrofitManager;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignModel implements LoginModelInter {


    @Override
    public void registerDo(RegLoginInfo regLoginInfo, final ModelCallBack modelCallBack) {
        RetrofitManager.getInstance().createRs(RegisterDoApiService.class)
                .register(regLoginInfo)
                .enqueue(new Callback<Result>() {
                    @Override
                    public void onResponse(Call<Result> call, Response<Result> response) {
                        if (response.body()!=null){
                            modelCallBack.onSucceed(response.body().getResult());
                        }else modelCallBack.onFail("body==null");
                    }

                    @Override
                    public void onFailure(Call<Result> call, Throwable t) {
                        modelCallBack.onFail("onFail");
                    }
                });
    }

    @Override
    public void registerAlready(String phoneNum, ModelCallBack modelCallBack) {

    }

    @Override
    public void registerGetCode(String phone, final ModelCallBack modelCallBack) {
        RetrofitManager.getInstance().createRs(RegisterGetCodeApiService.class)
                .getRegisterCode(phone)
                .enqueue(new Callback<Result>() {
                    @Override
                    public void onResponse(Call<Result> call, Response<Result> response) {
                        if (response.body()!=null) modelCallBack.onSucceed(response.body().getResult());
                        else modelCallBack.onFail("body==null");
                    }

                    @Override
                    public void onFailure(Call<Result> call, Throwable t) {
                        modelCallBack.onFail("onFailed");
                    }
                });
    }

    @Override
    public void loginDo(RegLoginInfo regLoginInfo, final ModelCallBack modelCallBack) {
        RetrofitManager.getInstance().createRs(LoginDoApiService.class)
                .Login(regLoginInfo)
                .enqueue(new Callback<Result>() {
                    @Override
                    public void onResponse(Call<Result> call, Response<Result> response) {
                        if (response.body()!=null){
                            modelCallBack.onSucceed(response.body().getResult());
                        }else modelCallBack.onFail("body==null");
                    }

                    @Override
                    public void onFailure(Call<Result> call, Throwable t) {
                        modelCallBack.onFail("onFail");
                    }
                });
    }

    @Override
    public void loginGetCode(String phoneNum, final ModelCallBack modelCallBack) {
        RetrofitManager.getInstance().createRs(LoginGetImageCodeApiService.class)
                .getImageCode(phoneNum)
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.body()!=null){
                             InputStream i = response.body().byteStream();
                             Bitmap bitmap = BitmapFactory.decodeStream(i);

                            modelCallBack.onSucceed(bitmap);
                        }else{
                            modelCallBack.onFail("body==null");
                        }

                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        modelCallBack.onFail("onFail");
                    }
                });
    }
}
