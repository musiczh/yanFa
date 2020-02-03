package com.example.yanfa.presenter;

/**
 * 报名界面的BasePresenter层
 */
public class EnrollBasePresenter<V> {
    private V view;

    public void attachView(V view){
        this.view = view;
    }
    public void detachView(){
        view = null;
    }

    public V getMvpView(){
        return view;
    }

    public boolean isAttachView(){
        return view!=null;
    }
}
