package com.example.yanfa.ui.fragment;


import android.app.AlertDialog;
import android.content.DialogInterface;
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

import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.yanfa.R;
import com.example.yanfa.bean.EnrollBean;
import com.example.yanfa.contract.IEnrollContract;
import com.example.yanfa.presenter.EnrollBasePresenter;
import com.example.yanfa.presenter.EnrollPresenter;
import com.example.yanfa.util.ToastUtils;
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
    private Boolean mGender;
    private String mDirection;
    private String mFacultyEdt;

    private RadioButton mAndroidBtn;
    private RadioButton mWebBtn;
    private RadioButton mJavaBtn;
    private RadioButton mBigDataBtn;
    private String mMySelfEdt = " ";
    private Button mSetUpBtn;
    private EditText editText,editTex2,editText3,editText4,editText5,editText6,editText7;
    private EnrollBasePresenter mPresenter;

    private RadioGroup mWebDataRgp;
    private RadioGroup mJavaAndroidRap;
    private TextInputLayout mNameTextLay;
    private TextInputLayout mStuNumTextLay;
    private TextInputLayout mMajorTextLay;
    private TextInputLayout mGradeTextLay;
    private TextInputLayout mQQTextLay;
    private TextInputLayout mFacultyLay;

    private EnrollBean mEnrollBean;
    private ProgressBar mProgressBar;
    private boolean sexFlag = false;
    private boolean directionFlag = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_enroll,container,false);
        initView(view);
        return view;

    }

    private void initView(View view){

        mBoyCkBox = view.findViewById(R.id.boy_checkBox);
        mGirlCkBox = view.findViewById(R.id.girl_checkBox);

        editText = view.findViewById(R.id.name_editText);
        editTex2 = view.findViewById(R.id.student_number_editText);
        editText3 = view.findViewById(R.id.major_editText);
        editText4= view.findViewById(R.id.grade_editText);
        editText5 = view.findViewById(R.id.qq_editText);
        editText6 = view.findViewById(R.id.self_edt);
        editText7 = view.findViewById(R.id.faculty_editText);

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
        mFacultyLay = view.findViewById(R.id.faculty_textInputLayout);

        mProgressBar = view.findViewById(R.id.progressBar);

        mJavaBtn.setOnCheckedChangeListener(this);
        mAndroidBtn.setOnCheckedChangeListener(this);
        mWebBtn.setOnCheckedChangeListener(this);
        mBigDataBtn.setOnCheckedChangeListener(this);


        mSetUpBtn = view.findViewById(R.id.set_up_button);
        Log.d("Enroll","initView");
        mSetUpBtn.setOnClickListener(this);

        mBoyCkBox.setOnCheckedChangeListener(this);
        mGirlCkBox.setOnCheckedChangeListener(this);

        mEnrollBean = new EnrollBean();

    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        switch (buttonView.getId()){
            case R.id.android_radioButton:
                mDirection="Android";
                mWebDataRgp.clearCheck();
                Log.d("Button","android");
                if(isChecked){
                    directionFlag = true;
                }else {
                    directionFlag = false;
                }
                break;
            case R.id.java_radioButton:
                mWebDataRgp.clearCheck();
                mDirection = "后台";
                if(isChecked){
                    directionFlag = true;
                }else {
                    directionFlag = false;
                }
                break;
            case R.id.big_data_radioButton:
                mJavaAndroidRap.clearCheck();
                mDirection = "大数据";
                if(isChecked){
                    directionFlag = true;
                }else {
                    directionFlag = false;
                }
                break;
            case R.id.web_radioButton:
                mJavaAndroidRap.clearCheck();
                mDirection = "前端";
                if(isChecked){
                    directionFlag = true;
                }else {
                    directionFlag = false;
                }
                break;
            case R.id.boy_checkBox:
                if(isChecked){
                    mGirlCkBox.setChecked(false);
                    mGender = true;
                    sexFlag = true;
                }else {
                    sexFlag = false;
                }

                break;
            case R.id.girl_checkBox:
                if(isChecked){
                    mBoyCkBox.setChecked(false);
                    mGender = false;
                    sexFlag = true;
                }else {
                    sexFlag = false;
                }
                break;
            default:
                break;

        }
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
                    mFacultyLay.setErrorEnabled(false);

                    mNameEdt = editText.getText().toString().trim();
                    mStuNumEdt = editTex2.getText().toString().trim();
                    mMajorEdt = editText3.getText().toString().trim();
                    mGradeEdt = editText4.getText().toString().trim();
                    mQqEdt = editText5.getText().toString().trim();
                    mMySelfEdt = editText6.getText().toString().trim();
                    mFacultyEdt = editText7.getText().toString().trim();

                    //判断内容否为空
                    if(mNameEdt.equals("")){
                        mNameTextLay.setError("内容不能为空");
                    }else {
                        mEnrollBean.setName(mNameEdt);
                    }
                    if(mQqEdt.equals("")){
                        mQQTextLay.setError("内容不能为空");
                    }else {
                        mEnrollBean.setQq(mQqEdt);
                    }
                    if(mStuNumEdt.equals("")){
                         mStuNumTextLay.setError("内容不能为空");
                    }else {
                        mEnrollBean.setSno(mStuNumEdt);
                    }
                    if(mMajorEdt.equals("")){
                        mMajorTextLay.setError("内容不能为空");
                    }else {
                        mEnrollBean.setMajor(mMajorEdt);
                    }
                    if(mGradeEdt.equals("")){
                        mGradeTextLay.setError("内容不能为空");
                    }else {
                        mEnrollBean.setSex(mGender);
                    }
                    if(mFacultyEdt.equals("")){
                        mFacultyLay.setError("内容不能为空");
                    }else {
                        mEnrollBean.setFaculty(mFacultyEdt);
                    }

                    mEnrollBean.setOpenid("");
                    mEnrollBean.setSelfIntroduction(mMySelfEdt);


                    //判断是否填写姓别
                    if(!sexFlag){
                        ToastUtils.showToast(getActivity(),"请选择你的性别");
                    } else if(!directionFlag){
                        ToastUtils.showToast(getActivity(),"请选择你报名的方向");
                    }

                    //若内容全有则可报名
                    if(!(mStuNumEdt.equals("")||mGradeEdt.equals("")||mMajorEdt.equals("")
                            &&mQqEdt.equals("")||mNameEdt.equals("")||mFacultyEdt.equals(""))){
                        mPresenter = new EnrollPresenter();
                        ((EnrollPresenter) mPresenter).enroll(mEnrollBean);
                        mProgressBar.setVisibility(View.VISIBLE);
                        mSetUpBtn.setVisibility(View.INVISIBLE);
                    }

                    break;
            }

    }

    /**
     *失败时调用
     */
    @Override
    public void onFailure(String str) {

    }

    /**
     * 成功时调用
     */
    @Override
    public void onSuccess(String str) {
        Log.d("fragment",str);
        mSetUpBtn.setVisibility(View.VISIBLE);
        mProgressBar.setVisibility(View.GONE);
        dialogBox();
    }

    //弹出对话框
    private void dialogBox() {
        AlertDialog.Builder bb = new AlertDialog.Builder(getContext());
        bb.setMessage("报名成功！");
        bb.setTitle("提示");
        bb.setCancelable(true);
        bb.setPositiveButton("查看考核", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        bb.show();
    }



}
