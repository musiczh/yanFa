package com.example.yanfa.ui.fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.yanfa.R;
import com.example.yanfa.contract.IEnrollContract;
import com.example.yanfa.presenter.EnrollBasePresenter;
import com.example.yanfa.presenter.EnrollPresenter;
import com.google.android.material.textfield.TextInputLayout;


/**
 * 报名fragment
 */
public class EnrollFragment extends Fragment implements View.OnClickListener, IEnrollContract.IView,  CompoundButton.OnCheckedChangeListener {


    private String mNameEdt;
    private CheckBox mBoyCkBox;
    private CheckBox mGirlCkBox;
    private String mStuNumEdt="";
    private String mMajorEdt;
    private String mGradeEdt;
    private String mQqEdt;
    private String mGender;
    private String mDirection;

    private RadioButton mAndroidBtn;
    private RadioButton mWebBtn;
    private RadioButton mJavaBtn;
    private RadioButton mBigDataBtn;
    private String mMySelfEdt;
    private Button mSetUpBtn;
    private EditText editText,editTex2,editText3,editText4,editText5,editText6;
    private EnrollBasePresenter mPresenter;

    private RadioGroup mWebDataRgp;
    private RadioGroup mJavaAndroidRap;
    private TextInputLayout mNameTextLay;
    private TextInputLayout mStuNumTextLay;
    private TextInputLayout mMajorTextLay;
    private TextInputLayout mGradeTextLay;
    private TextInputLayout mQQTextLay;
    private boolean isChecked = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_enroll,container,false);
        initView(view);
        return view;

    }

    private void initView(View view){


        editText = view.findViewById(R.id.name_editText);
        mBoyCkBox = view.findViewById(R.id.boy_checkBox);
        mGirlCkBox = view.findViewById(R.id.girl_checkBox);
        editTex2 = view.findViewById(R.id.student_number_editText);
        editText3 = view.findViewById(R.id.major_editText);
        editText4= view.findViewById(R.id.grade_editText);
        editText5 = view.findViewById(R.id.qq_editText);

        mWebDataRgp = view.findViewById(R.id.radioGroup);
        mJavaAndroidRap = view.findViewById(R.id.radioGroup2);
        mAndroidBtn = view.findViewById(R.id.web_radioButton);
        mWebBtn = view.findViewById(R.id.android_radioButton);
        mJavaBtn = view.findViewById(R.id.java_radioButton);
        mBigDataBtn = view.findViewById(R.id.big_data_radioButton);

        mNameTextLay = view.findViewById(R.id.name_textInputLayout);
        mGradeTextLay = view.findViewById(R.id.grade_textInputLayout);
        mMajorTextLay = view.findViewById(R.id.major_textInputLayout);
        mStuNumTextLay = view.findViewById(R.id.stu_num_textInputLayout);
        mQQTextLay = view.findViewById(R.id.qq_textInputLayout);
        mJavaBtn.setOnCheckedChangeListener(this);
        mAndroidBtn.setOnCheckedChangeListener(this);
        mWebBtn.setOnCheckedChangeListener(this);
        mBigDataBtn.setOnCheckedChangeListener(this);

        editText6 = view.findViewById(R.id.self_edt);
        mSetUpBtn = view.findViewById(R.id.set_up_button);
        Log.d("Enroll","initView");
        mSetUpBtn.setOnClickListener(this);

        mBoyCkBox.setOnCheckedChangeListener(this);
        mGirlCkBox.setOnCheckedChangeListener(this);

        mPresenter = new EnrollPresenter();

    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }

    @Override
    public void onClick(View v) {
            switch (v.getId()){
                case R.id.set_up_button:
                    Log.d("Enroll","点击按钮");
                    mNameTextLay.setErrorEnabled(false);
                    mQQTextLay.setErrorEnabled(false);
                    mMajorTextLay.setErrorEnabled(false);
                    mGradeTextLay.setErrorEnabled(false);
                    mStuNumTextLay.setErrorEnabled(false);
                    mNameEdt = editText.getText().toString().trim();
                    mStuNumEdt = editTex2.getText().toString().trim();
                    mMajorEdt = editText3.getText().toString().trim();
                    mGradeEdt = editText4.getText().toString().trim();
                    mQqEdt = editText5.getText().toString().trim();
                    mMySelfEdt = editText6.getText().toString().trim();
                    mSetUpBtn.setOnClickListener(this);
                    if(mNameEdt.equals("")){
                        mNameTextLay.setError("内容不能为空");
                    }
                    if(mQqEdt.equals("")){
                        mQQTextLay.setError("内容不能为空");
                    }
                    if(mStuNumEdt.equals("")){
                         mStuNumTextLay.setError("内容不能为空");
                    }
                    if(mMajorEdt.equals("")){
                        mMajorTextLay.setError("内容不能为空");
                    }
                    if(mGradeEdt.equals("")){
                        mGradeTextLay.setError("内容不能为空");
                    }
                   /* Log.d("Edt",mNameEdt);
                    Log.d("Edt",mStuNumEdt);
                    Log.d("Edt",mMajorEdt);
                    Log.d("Edt",mGradeEdt);
                    Log.d("Edt", mQqEdt);
                    Log.d("Edt",mMySelfEdt);
                    Log.d("Edt",mGender);
                    Log.d("Edt",mDirection);*/


                    break;
            }

    }

    /**
     *失败时调用
     */
    @Override
    public void onFailure() {

    }

    /**
     * 成功时调用
     */
    @Override
    public void onSuccess() {

    }



    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        switch (buttonView.getId()){
            case R.id.android_radioButton:
                mDirection="Android";
                mWebDataRgp.clearCheck();
                Log.d("Button","android");
                break;
            case R.id.java_radioButton:
                mWebDataRgp.clearCheck();
                mDirection = "后台";
                break;
            case R.id.big_data_radioButton:
                mJavaAndroidRap.clearCheck();
                mDirection = "大数据";
                break;
            case R.id.web_radioButton:
                mJavaAndroidRap.clearCheck();
                mDirection = "前端";
                break;
            case R.id.boy_checkBox:
                if(isChecked){
                    mGirlCkBox.setChecked(false);
                    mGender = "男";
                }else {

                }

                break;
            case R.id.girl_checkBox:
                if(isChecked){
                    mBoyCkBox.setChecked(false);
                    mGender = "女";
                }else {

                }
                break;
                default:
                    break;

        }
    }

}
