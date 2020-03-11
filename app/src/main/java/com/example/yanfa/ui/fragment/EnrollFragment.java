package com.example.yanfa.ui.fragment;


import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

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

import com.example.yanfa.MainActivity;
import com.example.yanfa.R;
import com.example.yanfa.bean.EnrollBean;
import com.example.yanfa.bean.Result;
import com.example.yanfa.contract.IEnrollContract;
import com.example.yanfa.iApiService.EnrollApiService;
import com.example.yanfa.iApiService.IfEnrollApiService;
import com.example.yanfa.presenter.EnrollBasePresenter;
import com.example.yanfa.presenter.EnrollPresenter;
import com.example.yanfa.util.NetworkUtil;
import com.example.yanfa.util.RetrofitManager;
import com.example.yanfa.util.ToastUtils;
import com.google.android.material.textfield.TextInputLayout;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * 报名fragment
 */
public class EnrollFragment extends Fragment implements View.OnClickListener, IEnrollContract.IView,  CompoundButton.OnCheckedChangeListener {


    private String mNameEdt;
    private CheckBox mBoyCkBox;
    private CheckBox mGirlCkBox;
    private String mStuNumEdt="";
    private String mMajorEdt;
//    private String mGradeEdt;
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
    private TextInputLayout selfLay;

    private EnrollBean mEnrollBean;
//    private ProgressBar mProgressBar;
    private boolean sexFlag = false;  //是否选择性别
    private boolean directionFlag = false; //是否选择方向
    private boolean enrollFalg = false; //电话号码是否报名过
    private MainActivity mainActivity;
    private ProgressDialog pd;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_enroll,container,false);
        initView(view);
        return view;

    }

    @Override
    public void onResume() {
        super.onResume();
        mainActivity = (MainActivity) getActivity();
        if (!mainActivity.getIfLogin()) {
            dialogBox2();      //若没有登录则先登录
        }

        if (!NetworkUtil.isNetworkAvailable(getContext())){

            ToastUtils.showToast(getActivity(),"网络不可用");

        }else if(mainActivity.getIfLogin()){  //有登录则判断是否报名过
            ((EnrollPresenter) mPresenter).goIfEnroll(mainActivity.getPhoneNum());
        }

    }

    private void initView(View view){

        mBoyCkBox = view.findViewById(R.id.boy_checkBox);
        mGirlCkBox = view.findViewById(R.id.girl_checkBox);

        editText = view.findViewById(R.id.name_editText);
        editTex2 = view.findViewById(R.id.student_number_editText);
        editText3 = view.findViewById(R.id.major_editText);
//        editText4= view.findViewById(R.id.grade_editText);
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
//        mGradeTextLay = view.findViewById(R.id.grade_textInputLayout);
        mMajorTextLay = view.findViewById(R.id.major_textInputLayout);
        mStuNumTextLay = view.findViewById(R.id.stu_num_textInputLayout);
        mQQTextLay = view.findViewById(R.id.qq_textInputLayout);
        mFacultyLay = view.findViewById(R.id.faculty_textInputLayout);
        selfLay = view.findViewById(R.id.self_textInputLayout);
//        mProgressBar = view.findViewById(R.id.progressBar);

        mJavaBtn.setOnCheckedChangeListener(this);
        mAndroidBtn.setOnCheckedChangeListener(this);
        mWebBtn.setOnCheckedChangeListener(this);
        mBigDataBtn.setOnCheckedChangeListener(this);


        mSetUpBtn = view.findViewById(R.id.set_up_button);
        Log.d("Enroll","initView");
        mSetUpBtn.setOnClickListener(this);

        mBoyCkBox.setOnCheckedChangeListener(this);
        mGirlCkBox.setOnCheckedChangeListener(this);

         mainActivity = (MainActivity) getActivity();

        mEnrollBean = new EnrollBean();
        mPresenter = new EnrollPresenter();
        mPresenter.attachView(this);
        Log.d("1","1"+mainActivity.getPhoneNum());

        pd=new ProgressDialog(getContext());
        pd.setMessage("正在上传...");





    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        switch (buttonView.getId()){
            case R.id.android_radioButton:
                mDirection="安卓";
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
                    mGender = true;
                    mGirlCkBox.setChecked(false);
                    sexFlag = true;
                }else {
                    sexFlag = false;
                }

                break;
            case R.id.girl_checkBox:

                if(isChecked){
                    mGender = false;
                    mBoyCkBox.setChecked(false);
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


                    if (!NetworkUtil.isNetworkAvailable(getContext())){

                        ToastUtils.showToast(getActivity(),"网络不可用");

                    }else if(mainActivity.getIfLogin()){  //有登录则判断是否报名过
                        ((EnrollPresenter) mPresenter).goIfEnroll(mainActivity.getPhoneNum());
                    }

                   /* mNameTextLay.setErrorEnabled(false);
                    mQQTextLay.setErrorEnabled(false);
                    mMajorTextLay.setErrorEnabled(false);
                    mGradeTextLay.setErrorEnabled(false);
                    mStuNumTextLay.setErrorEnabled(false);
                    mFacultyLay.setErrorEnabled(false);*/

                    mNameEdt = editText.getText().toString().trim();
                    mStuNumEdt = editTex2.getText().toString().trim();
                    mMajorEdt = editText3.getText().toString().trim();
//                    mGradeEdt = editText4.getText().toString().trim();
                    mQqEdt = editText5.getText().toString().trim();
                    mMySelfEdt = editText6.getText().toString().trim();
                    mFacultyEdt = editText7.getText().toString().trim();
                    //判断是否填写姓别
                    if(!sexFlag){
                        ToastUtils.showToast(getActivity(),"请选择你的性别");
                    } else{
                        mEnrollBean.setSex(mGender);
                    }

                    if(!directionFlag){
                        ToastUtils.showToast(getActivity(),"请选择你报名的方向");
                    }else {

                        mEnrollBean.setDirection(mDirection);

                    }

                    //判断内容否为空
                    if(mNameEdt.equals("")){
                        mNameTextLay.setErrorEnabled(true);
                        mNameTextLay.setError("内容不能为空");
                    }else {
                        mNameTextLay.setErrorEnabled(false);
                        mEnrollBean.setName(mNameEdt);
                    }
                    if(mQqEdt.equals("")){
                        mQQTextLay.setErrorEnabled(true);
                        mQQTextLay.setError("内容不能为空");
                    }else if(mQqEdt.length()<5||mQqEdt.length()>11) {
                        mQQTextLay.setErrorEnabled(true);
                        mQQTextLay.setError("请输入正常的QQ");
                    }else {
                        mQQTextLay.setErrorEnabled(false);
                        mEnrollBean.setQq(mQqEdt);
                    }
                    if(mStuNumEdt.equals("")){
                        mStuNumTextLay.setErrorEnabled(true);
                        mStuNumTextLay.setError("内容不能为空");
                    }else if(mStuNumEdt.length()!=10){
                        mStuNumTextLay.setErrorEnabled(true);
                        mStuNumTextLay.setError("请输入正确的学号");
                    }else {
                        mStuNumTextLay.setErrorEnabled(false);
                        mEnrollBean.setSno(mStuNumEdt);
                    }
                    if(mMajorEdt.equals("")){
                        mMajorTextLay.setErrorEnabled(true);
                        mMajorTextLay.setError("内容不能为空");
                    }else {
                        mMajorTextLay.setErrorEnabled(false);
                        mEnrollBean.setMajor(mMajorEdt);
                    }
                  /*  if(mGradeEdt.equals("")){
                        mGradeTextLay.setErrorEnabled(true);
                        mGradeTextLay.setError("内容不能为空");
                    }else {
                        mGradeTextLay.setErrorEnabled(false);
                        mEnrollBean.set(mGradeEdt);
                    }*/
                    if(mFacultyEdt.equals("")){
                        mFacultyLay.setErrorEnabled(true);
                        mFacultyLay.setError("内容不能为空");
                    }else {
                        mFacultyLay.setErrorEnabled(false);
                        mEnrollBean.setFaculty(mFacultyEdt);
                    }
                    if(mMySelfEdt.length()>120){
                        selfLay.setErrorEnabled(true);

                        selfLay.setError("字数超出限制");
                    }else {
                        selfLay.setErrorEnabled(false);
                        mEnrollBean.setSelfIntroduction(mMySelfEdt);
                    }

                    Log.d("测试","性别"+mEnrollBean.getSex());






                    //先判断有没有网络
                    if (!NetworkUtil.isNetworkAvailable(getContext())){

                        ToastUtils.showToast(getActivity(),"网络不可用");
                    }else if(!(mStuNumEdt.equals("")||mStuNumEdt.length()!=10||mMajorEdt.equals("")
                            ||mQqEdt.equals("")||mNameEdt.equals("")||mFacultyEdt.equals("")
                            ||mMySelfEdt.length()>120||mQqEdt.length()<5||mQqEdt.length()>11)){

                        //点击报名首先判断登录，登录状态才能继续下列操作
                            if(!mainActivity.getIfLogin()){
                                 dialogBox2();      //若没有登录则先登录
                            }else if(enrollFalg) {   //判断号码是否登录过，登录过弹出dialog
                              Log.d("报名过","if内");
                                dialogBox3();
                            } else{
                                apply();
                            }
                        }
                    break;
            }

    }

    /**
     *失败时调用
     */
    @Override
    public void onFailure(String str) {
        pd.dismiss();
        if(str.length()<60){
            ToastUtils.showToast(getActivity(),str);
        }else {
            Toast.makeText(getActivity(),"系统错误,请联系管理员",Toast.LENGTH_LONG).show();
        }

    }

    /**
     * 成功时调用
     */
    @Override
    public void onSuccess(String str) {
        Log.d("fragment",str);
//        mSetUpBtn.setVisibility(View.VISIBLE);
//        mProgressBar.setVisibility(View.GONE);
        pd.dismiss();
        dialogBox();
    }

    @Override
    public void ifEnroll(boolean flag) {
        Log.d("goIfEnroll","+++"+flag);
        enrollFalg = flag;
    }

    //弹出报名成功对话框
    private void dialogBox() {
        AlertDialog.Builder bb = new AlertDialog.Builder(getContext());
        bb.setMessage("报名成功！");
        bb.setTitle("提示");
        bb.setCancelable(true);
        bb.setPositiveButton("返回主界面", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mainActivity.backFragment();
            }
        });
        bb.setNegativeButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        bb.setCancelable(false);
        bb.show();
    }

    //弹出去登陆对话框
    private void dialogBox2() {
        AlertDialog.Builder bb = new AlertDialog.Builder(getContext());
        bb.setMessage("请先登录");
        bb.setTitle("提示");
        bb.setCancelable(true);
        bb.setPositiveButton("去登陆", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
               mainActivity.goLogin();
            }
        });
        bb.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mainActivity.backFragment();
            }
        });

        bb.show();
    }
    //弹出报名过对话框
    private void dialogBox3() {
        AlertDialog.Builder bb = new AlertDialog.Builder(getContext());
        bb.setMessage("你已经报名过了，请勿重复报名");
        bb.setTitle("提示");
        bb.setCancelable(true);
        bb.setPositiveButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mainActivity.backFragment();
            }
        });
        bb.setNegativeButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        bb.setCancelable(false);
        bb.show();
    }

    //缓冲的dialog
    public void circleDialog(){
        ProgressDialog pd=new ProgressDialog(getContext());
        pd.setMessage("正在上传...");
        pd.show();
    }



    //报名
    public void apply(){
        ((EnrollPresenter) mPresenter).enroll(mEnrollBean);
        pd.show();
        pd.setCanceledOnTouchOutside(false);
    }




}
