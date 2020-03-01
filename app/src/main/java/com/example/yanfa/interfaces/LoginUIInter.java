package com.example.yanfa.interfaces;

import android.graphics.Bitmap;

public interface LoginUIInter {
    public void showProgressBar();
    public void closeProgressBar();
    public void showToast(String toastString);
    public void setCodeImageView(Bitmap bitmap);
}
