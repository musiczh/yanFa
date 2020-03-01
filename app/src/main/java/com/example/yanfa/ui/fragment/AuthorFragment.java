package com.example.yanfa.ui.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.yanfa.MainActivity;
import com.example.yanfa.R;

/**
 * 关于作者fragment
 */

public class AuthorFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_author, container, false);
        ImageView imageView = view.findViewById(R.id.imageView_test);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity mainActivity= (MainActivity) getActivity();
                if (mainActivity!=null)
                Toast.makeText(getActivity(),mainActivity.getPhoneNum(),Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }

}
