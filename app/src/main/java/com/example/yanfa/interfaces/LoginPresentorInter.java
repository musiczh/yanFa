package com.example.yanfa.interfaces;

public interface LoginPresentorInter {
    public void sendCode(String phoneNum);
    public void Login(String phoneNum,String password);
    public void SignUp(String phoneNum,String password,String code);
    public void resetPassword(String phoneNum,String password,String code);
}
