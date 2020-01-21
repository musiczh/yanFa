package com.example.yanfa.ui.fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import android.widget.RadioButton;

import com.example.yanfa.R;




/**
 * 报名fragment
 */
public class EnrollFragment extends Fragment implements View.OnClickListener {


    private String mNameEdt;
    private CheckBox mBoyCkBox;
    private CheckBox mGirlCkBox;
    private String mStuNumEdt;
    private String mMajorEdt;
    private String mGradeEdt;
    private String mPhoneEdt;
    private RadioButton mAndroidBtn;
    private RadioButton mWebBtn;
    private RadioButton mJavaBtn;
    private RadioButton mBigDataBtn;
    private String mMySelfEdt;
    private Button mSetUpBtn;
    private EditText editText,editTex2,editText3,editText4,editText5,editText6;
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
        editText5 = view.findViewById(R.id.phone_number_editText);
        mAndroidBtn = view.findViewById(R.id.android_radioButton);
        mWebBtn = view.findViewById(R.id.web_radioButton);
        mJavaBtn = view.findViewById(R.id.java_radioButton);
        mBigDataBtn = view.findViewById(R.id.big_data_radioButton);
        editText6 = view.findViewById(R.id.self_edt);
        mSetUpBtn = view.findViewById(R.id.set_up_button);
        Log.d("Enroll","initView");
        mSetUpBtn.setOnClickListener(this);



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
                    mNameEdt = editText.getText().toString();
                    mStuNumEdt = editTex2.getText().toString();
                    mMajorEdt = editText3.getText().toString();
                    mGradeEdt = editText4.getText().toString();
                    mPhoneEdt = editText5.getText().toString();
                    mMySelfEdt = editText6.getText().toString();
                    mSetUpBtn.setOnClickListener(this);
                    Log.d("Edt",mNameEdt);
                    Log.d("","");
                    break;
            }

    }
}
