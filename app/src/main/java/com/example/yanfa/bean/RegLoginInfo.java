package com.example.yanfa.bean;

/**
 * 登录注册需要的Body对象
 * 主要是包含登录需要的信息
 */
public class RegLoginInfo {
    private String phoneNum;
    private String verifyCode;
    private String pwd;

    public RegLoginInfo(String phoneNum,String pwd,String verifyCode){
        this.phoneNum = phoneNum;
        this.verifyCode = verifyCode;
        this.pwd = pwd;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getVerifyCode() {
        return verifyCode;
    }

    public void setVerifyCode(String verifyCode) {
        this.verifyCode = verifyCode;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
}
