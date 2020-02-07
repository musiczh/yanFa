package com.example.yanfa.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.yanfa.R;
import com.example.yanfa.interfaces.LoginUIInter;
import com.example.yanfa.presenter.SignPresenter;
import com.google.android.material.textfield.TextInputEditText;

import static com.example.yanfa.util.URLStaticQuality.BASE_URL;
import static com.example.yanfa.util.URLStaticQuality.LOGIN_IMAGE_CODE;

/**
 * 登录相关的activity
 */
public class LoginActivity extends AppCompatActivity implements LoginUIInter {
    public static final int SIGN_UP = 1;
    public static final int RESET_PASSWORD = 0;

    private String phoneNum;
    private String password;
    private String code;

    private ImageView imageViewCode;
    private ProgressBar progressBar ;

    private TextView textView_code;

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

        textView_code = findViewById(R.id.textView_code);

        progressBar = findViewById(R.id.LoginProgressBar);

        final TextInputEditText textInputEditTextPhone = findViewById(R.id.textInputEditText_account);
        final TextInputEditText textInputEditTextPassword = findViewById(R.id.textInputEditText_password);
        final TextInputEditText textInputEditTextCode = findViewById(R.id.textInputEditText_code);

        imageViewCode = findViewById(R.id.imageView_code);

        imageViewCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phoneNum = String.valueOf(textInputEditTextPhone.getText());
                if (phoneNum.length()==11) {
                    presenter.setImageViewCode(phoneNum);

                }
                else showToast("请输入正确的手机号");
            }
        });

        textInputEditTextPhone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String phoneNum = String.valueOf(s);
                if (phoneNum.length()==11)
                presenter.setImageViewCode(phoneNum);


            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        //去注册按钮设置点击事件监听
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
//        TextView textViewForget = findViewById(R.id.textView_forget);
//        textViewForget.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(LoginActivity.this,SignUpActivity.class);
//                intent.putExtra("type",RESET_PASSWORD);
//                startActivity(intent);
//                finish();
//            }
//        });

        //登录按钮设置点击事件
        Button buttonLogin = findViewById(R.id.button_login);
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                phoneNum = String.valueOf(textInputEditTextPhone.getText());
                password = String.valueOf(textInputEditTextPassword.getText());
                code = String.valueOf(textInputEditTextCode.getText());
                if (phoneNum.length()!=11) textInputEditTextPhone.setError("请输入正确的手机号码");
                else if (code.length()==0) textInputEditTextPassword.setError("密码不能为空");
                else if(password.length()==0) textInputEditTextCode.setError("验证码不能为空");
                else
                presenter.Login(phoneNum,password,code);
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
        if (toastString!=null&&toastString.equals("登录成功")){

            setResult(2);
            this.finish();
        }
    }



    @Override
    public void setCodeImageView(Bitmap bitmap) {
        imageViewCode.setImageBitmap(bitmap);
        if (textView_code.getVisibility()==View.VISIBLE)
            textView_code.setVisibility(View.GONE);
    }

}
