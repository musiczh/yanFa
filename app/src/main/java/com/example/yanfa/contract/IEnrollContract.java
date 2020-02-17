package com.example.yanfa.contract;

import com.example.yanfa.bean.EnrollBean;

import retrofit2.http.Body;

/**
 * 报名界面的Contract层
 */
public class IEnrollContract {
    public interface  IView<T>{
        void onFailure(String str);
        void onSuccess(String str);
    }
    public interface  IPresenter<T>{
        void onFailure(String str);
        void onSuccess(String str);

        void  enroll(EnrollBean enrollBean);
    }
    public interface IModel{
        void  enroll(EnrollBean enrollBean);
    }
}
