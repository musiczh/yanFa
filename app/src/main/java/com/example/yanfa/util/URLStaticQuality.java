package com.example.yanfa.util;

public class URLStaticQuality {

    public static final String BASE_URL = "https://rdc.kim:888";

    //注册获取手机验证码的附加地址
    public static final String GET_REGISTER_CODE = "/recruitment/register/getCode";

    //注册的附加地址
    public static final String REGISTER_DO = "/recruitment/register/do";

    //获取图片验证码的附加地址
    public static final String LOGIN_IMAGE_CODE = "/recruitment/verify";

    //登录的附加地址
    public static final String LOGIN_DO = "/recruitment/login/phone";

    //报名的附加地址
    public static final String ENROLL_DO = "/recruitment/commit/do";

    //查看是否报名附加地址
    public static final String IF_ENROLL_DO = "/recruitment/commit/alreadyInPhone";

    //通知的附加地址
    public static final String NOTICE = "/recruit/result/notice";
}
