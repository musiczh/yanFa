package com.example.yanfa.bean;

public class EnrollBean {
    private String openid;

    private String name;

    private boolean sex;

    private String faculty;

    private String major;

    private String qq;

    private String sno;

    private String direction;

    private String selfIntroduction;



    public void setOpenid(String openid){
        this.openid = openid;
    }
    public String getOpenid(){
        return this.openid;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return this.name;
    }
    public void setSex(boolean sex){
        this.sex = sex;
    }
    public boolean getSex(){
        return this.sex;
    }
    public void setFaculty(String faculty){
        this.faculty = faculty;
    }
    public String getFaculty(){
        return this.faculty;
    }
    public void setMajor(String major){
        this.major = major;
    }
    public String getMajor(){
        return this.major;
    }
    public void setQq(String qq){
        this.qq = qq;
    }
    public String getQq(){
        return this.qq;
    }
    public void setSno(String sno){
        this.sno = sno;
    }
    public String getSno(){
        return this.sno;
    }
    public void setDirection(String direction){
        this.direction = direction;
    }
    public String getDirection(){
        return this.direction;
    }
    public void setSelfIntroduction(String selfIntroduction){
        this.selfIntroduction = selfIntroduction;
    }
    public String getSelfIntroduction(){
        return this.selfIntroduction;
    }
}
