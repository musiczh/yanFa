package com.example.yanfa.interfaces;

/**
 * MainActivity需要提供的方法接口
 */
public interface MainActivityInter {
    //跳转登录界面接口，登录成功在里面处理逻辑
     void goLogin();

    //返回上一个碎片
     void backFragment();

    //获取是否已经登录；true已登录，false未登录
     boolean getIfLogin();

     //获取手机登录手机号码.返回为null即为未登录成功
    String getPhoneNum();
}
