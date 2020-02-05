package com.example.yanfa.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yanfa.R;
import com.example.yanfa.interfaces.LoginUIInter;
import com.example.yanfa.presentor.SignPresenter;
import com.google.android.material.textfield.TextInputEditText;

/**
 * 登录相关的activity
 */
public class LoginActivity extends AppCompatActivity implements LoginUIInter {
    public static final int SIGN_UP = 1;
    public static final int RESET_PASSWORD = 0;

    private String phoneNum;
    private String password;

    private ProgressBar progressBar ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //修改界面主题，让字体显示黑色
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            this.getWindow().getDecorView().setSystemUiVisibility( View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
        setContentView(R.layout.activity_login);

        //给presenter添加view
        final SignPresenter presenter = new SignPresenter();
        presenter.attachView(this);

        progressBar = findViewById(R.id.LoginProgressBar);

        //注册按钮设置点击事件监听
        TextView textViewSignUp = findViewById(R.id.textView_sign_up);
        textViewSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,SignUpActivity.class);
                intent.putExtra("type",SIGN_UP);
                startActivity(intent);
            }
        });

        //忘记密码按钮设置点击事件
        TextView textViewForget = findViewById(R.id.textView_forget);
        textViewForget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,SignUpActivity.class);
                intent.putExtra("type",RESET_PASSWORD);
                startActivity(intent);
                finish();
            }
        });

        //登录按钮设置点击事件
        Button buttonLogin = findViewById(R.id.button_login);
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextInputEditText textInputEditTextPhone = findViewById(R.id.textInputEditText_account);
                TextInputEditText textInputEditTextPassword = findViewById(R.id.textInputEditText_password);
                phoneNum = String.valueOf(textInputEditTextPhone.getText());
                password = String.valueOf(textInputEditTextPassword.getText());
                presenter.Login(phoneNum,password);
            }
        });


    }

    @Override
    public void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void closeProgressBar() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showToast(String toastString) {
        Toast.makeText(this,toastString,Toast.LENGTH_SHORT).show();
    }

}
