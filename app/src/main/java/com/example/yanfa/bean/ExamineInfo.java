package com.example.yanfa.bean;

import com.orient.me.data.ITimeItem;

public class ExamineInfo implements ITimeItem {
    private String stage;
    private String msg;
    private int color;

    public ExamineInfo(String stage, String msg, int color) {
        this.stage = stage;
        this.msg = msg;
        this.color = color;
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


    public void setColor(int color) {
        this.color = color;
    }

    @Override
    public String getTitle() {
        return null;
    }

    @Override
    public int getColor() {
        return color;
    }

    @Override
    public int getResource() {
        return 0;
    }
}
