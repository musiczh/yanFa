package com.example.yanfa.ui.fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.yanfa.MainActivity;
import com.example.yanfa.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

/**
 * 主界面fragment
 */
public class MainFragment extends Fragment {



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        //悬浮按钮设置监听事件。跳转报名界面
        FloatingActionButton floatingActionButton = view.findViewById(R.id.floatingActionButton_main);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getActivity()!=null) {
                    NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
                    navController.navigate(R.id.action_nav_main_to_nav_enroll);
                }
            }
        });
        return view;
    }

}
