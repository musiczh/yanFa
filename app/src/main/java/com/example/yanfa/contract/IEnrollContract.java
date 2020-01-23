package com.example.yanfa.contract;

/**
 * 报名界面的Contract层
 */
public class IEnrollContract {
    public interface  IView<T>{
        void onFailure();
        void onSuccess();
    }
    public interface  IPresenter<T>{
        void onSuccess();
        void onFailure();

        void setUp(String name,String gender,String studentNumber,String major,String grade,
                   String phoneNumber,String direction,String mySelf);
    }
    public interface IModel{
        void setUp(String name,String gender,String studentNumber,String major,String grade,
                   String phoneNumber,String direction,String mySelf);
    }
}
