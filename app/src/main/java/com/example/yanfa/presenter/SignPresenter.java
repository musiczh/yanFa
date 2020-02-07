package com.example.yanfa.presenter;

import android.graphics.Bitmap;

import com.example.yanfa.bean.RegLoginInfo;
import com.example.yanfa.iApiService.RegisterDoApiService;
import com.example.yanfa.interfaces.BasePresentor;
import com.example.yanfa.interfaces.LoginPresentorInter;
import com.example.yanfa.interfaces.LoginUIInter;
import com.example.yanfa.interfaces.ModelCallBack;
import com.example.yanfa.model.SignModel;

public class SignPresenter extends BasePresentor<LoginUIInter> implements LoginPresentorInter {
    private SignModel mSignModel = new SignModel();
    private boolean ifCanSendCode = true;

    //注册界面发送验证码的按钮逻辑
    @Override
    public void sendCode(String phoneNum) {
        if (ifCanSendCode){
            ifCanSendCode = false;
            getView().showProgressBar();
            if(correctData(phoneNum,"password")){
                mSignModel.registerGetCode(phoneNum, new ModelCallBack() {
                    @Override
                    public void onSucceed(Object result) {
                        String s = (String)result;
                        if (s.equals("ok")) getView().showToast("获取成功，请注意查收");
                        else getView().showToast(s);
                        getView().closeProgressBar();
                    }

                    @Override
                    public void onFail(Object result) {
                        getView().showToast("获取失败，请重新发送");
                        getView().closeProgressBar();
                    }
                });
            }else getView().closeProgressBar();

            //验证码发送间隔
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(10000);
                        ifCanSendCode = true;
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }else{
            getView().showToast("您发送验证码太频繁了");
        }


    }

    @Override
    public void Login(String phoneNum, String password,String code) {
        if(correctData(phoneNum,password)){
            getView().showProgressBar();
            final RegLoginInfo regLoginInfo = new RegLoginInfo(phoneNum,password,code);
            mSignModel.loginDo(regLoginInfo, new ModelCallBack() {
                @Override
                public void onSucceed(Object result) {
                    String s = (String)result;
                    if (s==null) getView().showToast("登录成功");
                    else getView().showToast(s);
                    getView().closeProgressBar();
                }

                @Override
                public void onFail(Object result) {
                    getView().showToast("登录失败，请重新登录");
                    getView().closeProgressBar();
                }
            });
        }

    }

    @Override
    public void SignUp(String phoneNum, String password, String code) {
        getView().showProgressBar();
        if(correctData(phoneNum,password)){
            RegLoginInfo regLoginInfo = new RegLoginInfo(phoneNum,password,code);
            mSignModel.registerDo(regLoginInfo, new ModelCallBack() {
                @Override
                public void onSucceed(Object result) {
                    String s = (String)result;
                    if (s.equals("ok")) getView().showToast("注册成功");
                    else getView().showToast(s);
                    getView().closeProgressBar();
                }

                @Override
                public void onFail(Object result) {
                    getView().showToast("注册失败，请重新注册");
                    getView().closeProgressBar();
                }
            });
        }else getView().closeProgressBar();
    }

    @Override
    public void resetPassword(String phoneNum, String password, String code) {
        if(correctData(phoneNum,password)){

        }
    }

    @Override
    public void setImageViewCode(String phoneNum) {
        mSignModel.loginGetCode(phoneNum, new ModelCallBack() {
            @Override
            public void onSucceed(Object result) {
                Bitmap bitmap = (Bitmap)result;
                getView().setCodeImageView(bitmap);
            }

            @Override
            public void onFail(Object result) {
                //getView().showToast("获取验证码失败，请点击验证码重新获取");
                getView().showToast((String)result);
            }
        });
    }


    private boolean correctData(String phoneNum,String password){
        if (phoneNum.length()!=11) {
            getView().showToast("请输入正确的手机号码");
            return false;
        }
        else if (password==null) {
            getView().showToast("请输入密码");
            return false;
        }
        return true;
    }
}
