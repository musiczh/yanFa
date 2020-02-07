package com.example.yanfa.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.yanfa.R;
import com.example.yanfa.interfaces.LoginUIInter;
import com.example.yanfa.presenter.SignPresenter;
import com.google.android.material.textfield.TextInputEditText;

/**
 * 注册和重置密码需要的activity‘
 */
public class SignUpActivity extends AppCompatActivity implements LoginUIInter {
    private ProgressBar progressBar;
    private int type;
    private String password;
    private String phoneNum;
    private String code;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            this.getWindow().getDecorView().setSystemUiVisibility( View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
        setContentView(R.layout.activity_sign_up);

        //给presenter绑定view
        final SignPresenter signPresenter = new SignPresenter();
        signPresenter.attachView(this);

        progressBar = findViewById(R.id.LoginProgressBar);

        Button buttonSignUp = findViewById(R.id.button_sign_up);
        Button buttonSendCode = findViewById(R.id.button_code);
        final TextInputEditText textInputEditTextAccount = findViewById(R.id.textInputEditText_account);
        final TextInputEditText textInputEditTextPassword = findViewById(R.id.textInputEditText_password);
        final TextInputEditText textInputEditTextCode = findViewById(R.id.textInputEditText_code);

        //判断是要重置密码还是注册，默认注册
        type = getIntent().getIntExtra("type",1);
        if (type == LoginActivity.RESET_PASSWORD) buttonSignUp.setText("重置密码");

        //注册重置按钮设置点击事件
        buttonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                phoneNum = String.valueOf(textInputEditTextAccount.getText());
                password = String.valueOf(textInputEditTextPassword.getText());
                code = String.valueOf(textInputEditTextCode.getText());
                if (phoneNum.length()!=11) textInputEditTextAccount.setError("请输入正确的手机号码");
                else if (code.length()==0) textInputEditTextPassword.setError("密码不能为空");
                else if(password.length()==0) textInputEditTextCode.setError("验证码不能为空");
                else{
                    if(type==LoginActivity.SIGN_UP)
                        signPresenter.SignUp(phoneNum,password,code);
                    else if (type==LoginActivity.RESET_PASSWORD) signPresenter.resetPassword(phoneNum,password,code);
                }

            }
        });

        //发送验证码按钮设置监听
        buttonSendCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                phoneNum = String.valueOf(textInputEditTextAccount.getText());
                if (phoneNum.length()!=11) textInputEditTextAccount.setError("请输入正确的手机号码");
                else
                signPresenter.sendCode(phoneNum);
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
        if (toastString.equals("注册成功")) this.finish();
    }

    @Override
    public void setCodeImageView(Bitmap bitmap) {
        //注册页面不需要图片验证码
    }
}
