package com.example.yanfa.bean;

import com.orient.me.data.ITimeItem;

public class ExamineInfo implements ITimeItem {
    private String stage;
    private String msg;
    private int color;
    private int res;
    private boolean b;

    public ExamineInfo(String stage, String msg, int color,int res,boolean mboolean) {
        this.stage = stage;
        this.msg = msg;
        this.color = color;
        this.res = res;
        this.b = mboolean;
    }

    public String getStage() {
        return stage;
    }

    public void setStage(String name) {
        this.stage = stage;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String detail) {
        this.msg = msg;
    }

    public boolean getBoolean(){
        return b;
    }

    public void setBoolean(boolean b){
        this.b = b;
    }

    public int getRes(){
        return res;
    }

    public void setRes(int res){
        this.res = res;
    }

    public void setColor(int color) {
        this.color = color;
    }

    @Override
    public String getTitle() {
        return null;
    }

    @Override
    public int getResource() {
        return res;
    }
    @Override
    public int getColor() {
        return color;
    }

}
