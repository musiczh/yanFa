package com.example.yanfa.model;

import com.example.yanfa.contract.IEnrollContract;

/**
 * 报名界面的Model层
 */
public class EnrollModel implements IEnrollContract.IModel {
    private IEnrollContract.IPresenter mPresenter;

    public EnrollModel(IEnrollContract.IPresenter presenter){
        mPresenter = presenter;
    }

    @Override
    public void setUp(String name, String gender, String studentNumber, String major, String grade, String phoneNumber, String direction, String mySelf) {

    }
}
