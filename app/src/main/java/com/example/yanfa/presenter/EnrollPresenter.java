package com.example.yanfa.presenter;

import android.util.Log;

import com.example.yanfa.bean.EnrollBean;
import com.example.yanfa.contract.IEnrollContract;
import com.example.yanfa.model.EnrollModel;

/**
 * 报名界面的Presenter层
 */

public class EnrollPresenter extends EnrollBasePresenter<IEnrollContract.IView> implements IEnrollContract.IPresenter{
    private IEnrollContract.IModel mModel;


    public EnrollPresenter(){
        mModel = new EnrollModel(this);
    }
    @Override
    public void onSuccess(String str) {
        if(isAttachView()){
            getMvpView().onSuccess(str);
            Log.d("presenter",str);
        }
    }

    @Override
    public void onFailure(String str) {
        if(isAttachView()){
            getMvpView().onFailure(str);
        }
    }

    @Override
    public void enroll(EnrollBean enrollBean) {
        mModel.enroll(enrollBean);
    }
}
