package com.example.yanfa.ui.fragment;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.yanfa.MainActivity;
import com.example.yanfa.R;
import com.example.yanfa.ui.activity.DetailActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

/**
 * 主界面fragment
 */
public class MainFragment extends Fragment{
    public static final int ANDROID = 1;
    public static final int YANFA = 0;
    public static final int JAVA = 2;
    public static final int WEB = 3;
    public static final int DATA = 4;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        setListeners(view);



        return view;
    }

    private void setListeners(View view){
        view.findViewById(R.id.imageView_cardView_yanfa).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), DetailActivity.class);
                intent.putExtra("type",YANFA);
                startActivityForResult(intent,1);

            }
        });

        view.findViewById(R.id.imageView_cardView_java).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), DetailActivity.class);
                intent.putExtra("type",JAVA);
                startActivityForResult(intent,1);
            }
        });

        view.findViewById(R.id.imageView_cardView_web ).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), DetailActivity.class);
                intent.putExtra("type",WEB);
                startActivityForResult(intent,1);
            }
        });

        view.findViewById(R.id.imageView_cardView_android).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), DetailActivity.class);
                intent.putExtra("type",ANDROID);
                startActivityForResult(intent,1);
            }
        });

        view.findViewById(R.id.imageView_cardView_data).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), DetailActivity.class);
                intent.putExtra("type",DATA);
                startActivityForResult(intent,1);
            }
        });
    }


}
