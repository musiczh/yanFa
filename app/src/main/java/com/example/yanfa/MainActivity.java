 package com.example.yanfa;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.customview.widget.ViewDragHelper;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.yanfa.ui.activity.LoginActivity;
import com.example.yanfa.widget.DrawerLayoutNoSlidingConflict;
import com.google.android.material.navigation.NavigationView;
import java.lang.reflect.Field;
import java.util.Objects;

 /**
  * MainActivity
  */
 public class MainActivity extends AppCompatActivity {
     private AppBarConfiguration appBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //设置toolbar
        Toolbar toolbar = findViewById(R.id.toolBar_main);
        setSupportActionBar(toolbar);
        TextView textView = (TextView)toolbar.getChildAt(0);
        textView.getLayoutParams().width = LinearLayout.LayoutParams.MATCH_PARENT;
        textView.setGravity(Gravity.END);






        //更改DrawerLayout的侧滑距离
        DrawerLayoutNoSlidingConflict drawerLayout = findViewById(R.id.drawerLayout_main);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT)
            setDrawerLeftEdgeSize(this,drawerLayout,0.5f);

        //将DrawerLayout和AppBar联合起来
        appBarConfiguration = new AppBarConfiguration.Builder(R.id.nav_main,R.id.nav_examine).setDrawerLayout(drawerLayout).build();
        NavigationView navigationView = findViewById(R.id.navigationView_main);
        NavController navController = Navigation.findNavController(this,R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this,navController,appBarConfiguration);
        NavigationUI.setupWithNavController(navigationView,navController);


        //登录文字监听
        View view = navigationView.getHeaderView(0);
        TextView textView1 = view.findViewById(R.id.textView_header_layout);
        textView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

    }


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
}
