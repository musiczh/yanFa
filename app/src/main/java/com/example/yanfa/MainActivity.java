 package com.example.yanfa;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.customview.widget.ViewDragHelper;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.example.yanfa.interfaces.MainActivityInter;
import com.example.yanfa.ui.activity.LoginActivity;
import com.example.yanfa.widget.DrawerLayoutNoSlidingConflict;
import com.google.android.material.navigation.NavigationView;
import java.lang.reflect.Field;
import java.util.Objects;


 /**
  * MainActivity
  */
 public class MainActivity extends AppCompatActivity implements MainActivityInter {
     private AppBarConfiguration appBarConfiguration;
     private static Context mContext;
     private String phoneNum = null;
     private boolean ifLogin = false;
     private TextView textViewRegister;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            MainActivity.this.getWindow().getDecorView().setSystemUiVisibility( View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
        setContentView(R.layout.activity_main);

        mContext = this;

        //设置toolbar
        Toolbar toolbar = findViewById(R.id.toolBar_main);
        setSupportActionBar(toolbar);
        TextView textView = (TextView)toolbar.getChildAt(0);
        textView.getLayoutParams().width = LinearLayout.LayoutParams.MATCH_PARENT;
        textView.setGravity(Gravity.END);



        //更改DrawerLayout的侧滑距离
        DrawerLayoutNoSlidingConflict drawerLayout = findViewById(R.id.drawerLayout_main);
        setDrawerLeftEdgeSize(this,drawerLayout,0.2f);

        //将DrawerLayout和AppBar联合起来
        appBarConfiguration = new AppBarConfiguration.Builder(R.id.nav_main,R.id.nav_examine).setDrawerLayout(drawerLayout).build();
        NavigationView navigationView = findViewById(R.id.navigationView_main);
        NavController navController = Navigation.findNavController(this,R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this,navController,appBarConfiguration);
        NavigationUI.setupWithNavController(navigationView,navController);




        //登录文字以及图案设置监听
        View view = navigationView.getHeaderView(0);
        textViewRegister = view.findViewById(R.id.textView_header_layout);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ifLogin) openLoginAlert();
                else goLogin();
            }
        });

        //判断是否已经登录
        ifLogin = getIntent().getBooleanExtra("ifLogin",false);
        if (ifLogin){
            textViewRegister.setText("已登录,点击重新登录");
            phoneNum = this.getSharedPreferences("user",MODE_PRIVATE)
                    .getString("phoneNum","0");
        }
    }

    @Override
    public void goLogin(){
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        startActivityForResult(intent,0);
    }

     @Override
     public void backFragment() {
         NavController navController = Navigation.findNavController(this,R.id.nav_host_fragment);
         navController.navigateUp();
     }

     @Override
     public boolean getIfLogin() {
         return ifLogin;
     }

     @Override
     public String getPhoneNum() {
        if (phoneNum!=null)
         return phoneNum;
        else return null;
     }

     //弹出是否重新登录框
     private void openLoginAlert(){
         final AlertDialog.Builder bb = new AlertDialog.Builder(getContext());
         bb.setMessage("您已经登录了，是否重新登录？");
         bb.setTitle("提示");
         bb.setCancelable(true);
         bb.setPositiveButton("确认", new DialogInterface.OnClickListener() {
             @Override
             public void onClick(DialogInterface dialog, int which) {
                 goLogin();
             }
         });
         bb.setNegativeButton("取消", new DialogInterface.OnClickListener() {
             @Override
             public void onClick(DialogInterface dialog, int which) {
             }
         });
         bb.show();

     }


     //标题栏
     @Override
     public boolean onSupportNavigateUp() {
         NavController navController = Navigation.findNavController(this,R.id.nav_host_fragment);
         return NavigationUI.navigateUp(navController,appBarConfiguration)|| super.onSupportNavigateUp();
     }


     //利用反射改变DrawerLayout的可滑动距离
     @RequiresApi(api = Build.VERSION_CODES.KITKAT)
     public static void setDrawerLeftEdgeSize(Activity activity, DrawerLayoutNoSlidingConflict drawerLayout,
                                              float displayWidthPercentage) {
         if (activity == null || drawerLayout == null) return;
         try {
             //通过反射获取DrawerLayout中的ViewDragHelper变量
             Field leftDraggedField = Objects.requireNonNull(drawerLayout.getClass().getSuperclass()). getDeclaredField("mLeftDragger");
             leftDraggedField.setAccessible(true);
             ViewDragHelper leftDragged = (ViewDragHelper) leftDraggedField.get(drawerLayout);

             if (leftDragged!=null) {
                 //再通过这个ViewDragHelper对象获取edgeSize参数
                 Field edgeSizeField = leftDragged.getClass().getDeclaredField("mEdgeSize");
                 edgeSizeField.setAccessible(true);
                 int edgeSize = edgeSizeField.getInt(leftDragged);

                 //更改这个edgeSize参数。获取屏幕宽度并更改
                 DisplayMetrics dm = new DisplayMetrics();
                 activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
                 edgeSizeField.setInt(leftDragged, Math.max(edgeSize, (int) (dm.widthPixels * displayWidthPercentage)));
             }

         } catch (Exception e) {
             //setInt的过程中可能会有异常设置不成功
             e.printStackTrace();
         }
     }

     //详情页面点击报名按钮跳转报名界面
     public void turn_enroll(){
        NavController navController = Navigation.findNavController(this,R.id.nav_host_fragment);
        navController.navigate(R.id.nav_enroll);

     }

     @Override
     protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
         super.onActivityResult(requestCode, resultCode, data);
         if (resultCode == 1) turn_enroll();
         if (resultCode == 2) {
             textViewRegister.setText("已登录，点击重新登录");
             ifLogin = true;
             if (data != null) {
                 phoneNum = data.getStringExtra("phoneNum");
             }
         }
     }



     public static Context getContext(){
        return mContext;
     }
 }
