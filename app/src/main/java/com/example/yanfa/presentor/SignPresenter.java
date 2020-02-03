package com.example.yanfa.presentor;

import com.example.yanfa.interfaces.BasePresentor;
import com.example.yanfa.interfaces.LoginPresentorInter;
import com.example.yanfa.interfaces.LoginUIInter;

public class SignPresenter extends BasePresentor<LoginUIInter> implements LoginPresentorInter {

    @Override
    public void sendCode(String phoneNum) {
        if(correctData(phoneNum,"password")){

        }
    }

    @Override
    public void Login(String phoneNum, String password) {
        if(correctData(phoneNum,password)){

        }

    }

    @Override
    public void SignUp(String phoneNum, String password, String code) {
        if(correctData(phoneNum,password)){

        }
    }

    @Override
    public void resetPassword(String phoneNum, String password, String code) {
        if(correctData(phoneNum,password)){

        }
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
