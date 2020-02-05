package com.example.yanfa.ui.activity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.yanfa.ui.fragment.MainFragment;
import com.example.yanfa.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.Objects;

public class DetailActivity extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            DetailActivity.this.getWindow().getDecorView().setSystemUiVisibility( View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
//        }
        setContentView(R.layout.activity_detail);

        Toolbar toolbar = findViewById(R.id.toolbar_detail);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setHomeButtonEnabled(true);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        //获取对应控件的实例
        ImageView imageView = findViewById(R.id.imageView_detail_head);
        TextView textView = findViewById(R.id.textView_detail_introduction);
        FloatingActionButton floatingActionButton = findViewById(R.id.fab);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(1);
                finish();
            }
        });


        //判断是点击哪一个模块，使用对应的信息
        Intent intent = getIntent();
        int type = intent.getIntExtra("type",0);
        switch (type){
            case MainFragment.ANDROID:
                imageView.setImageResource(R.drawable.android);
                toolbar.setTitle(R.string.Android);
//                imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
                textView.setText(R.string.android_introduction);
                break;
            case MainFragment.YANFA:
                imageView.setImageResource(R.drawable.logo_b);
                toolbar.setTitle(R.string.yanfa_name);
                textView.setText(R.string.yanfa_introduction);
//                imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
                break;
            case MainFragment.JAVA:
                imageView.setImageResource(R.drawable.java);
                toolbar.setTitle(R.string.java);
                textView.setText(R.string.java_introduction);
                break;
            case MainFragment.WEB:
                toolbar.setTitle(R.string.web);
                imageView.setImageResource(R.drawable.web);
                textView.setText(R.string.web_introduction);
                break;
            case MainFragment.DATA:
                toolbar.setTitle(R.string.big_data);
                imageView.setImageResource(R.drawable.big_data);
                textView.setText(R.string.data_introduction);
                break;

        }
    }

    //顶部返回按钮的点击逻辑
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }
}
