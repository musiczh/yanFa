package com.example.yanfa.interfaces;

import android.graphics.Bitmap;

import com.example.yanfa.bean.RegLoginInfo;

/**
 * 登录注册model层的接口
 */
public interface LoginModelInter {
    public void registerDo(RegLoginInfo regLoginInfo,ModelCallBack modelCallBack);//注册
    public void registerAlready(String phoneNum,ModelCallBack modelCallBack);     //判断手机是否已经注册
    public void registerGetCode(String phone,ModelCallBack modelCallBack);        //获取注册需要的验证码

    public void loginDo(RegLoginInfo regLoginInfo,ModelCallBack modelCallBack);   //登录
    public void loginGetCode(String phoneNum,ModelCallBack modelCallBack);        //获取图片验证码
}
