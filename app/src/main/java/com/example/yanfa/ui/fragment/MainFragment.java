package com.example.yanfa.ui.fragment;


import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.yanfa.R;
import com.example.yanfa.ui.activity.DetailActivity;
import com.example.yanfa.widget.NewCardView;

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
        //setAnimator(view);


        return view;
    }

    private void setListeners(View view){


        view.findViewById(R.id.imageView_cardView_yanfa).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), DetailActivity.class);
                intent.putExtra("type",YANFA);
                startAct(intent);

            }
        });

        view.findViewById(R.id.imageView_cardView_java).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), DetailActivity.class);
                intent.putExtra("type",JAVA);
                startAct(intent);
            }
        });

        view.findViewById(R.id.imageView_cardView_web ).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), DetailActivity.class);
                intent.putExtra("type",WEB);
                startAct(intent);
            }
        });

        view.findViewById(R.id.imageView_cardView_android).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), DetailActivity.class);
                intent.putExtra("type",ANDROID);
                startAct(intent);
            }
        });

        view.findViewById(R.id.imageView_cardView_data).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), DetailActivity.class);
                intent.putExtra("type",DATA);
                startAct(intent);
            }
        });
    }

    private void startAct(Intent intent){
        startActivityForResult(intent,1);
        if (getActivity()!=null)
        getActivity().overridePendingTransition(R.anim.activity_start_anim,R.anim.activity_finish_anim);
    }

    private void setAnimator(View view){
        NewCardView yanfa = view.findViewById(R.id.cardView_yanfa);
        NewCardView android = view.findViewById(R.id.cardView_android);
        NewCardView data = view.findViewById(R.id.cardView_data);
        NewCardView web = view.findViewById(R.id.cardView_web);
        NewCardView java = view.findViewById(R.id.cardView_java);

        Animator objectAnimator = AnimatorInflater.loadAnimator(getActivity(),R.animator.card_shake);
        objectAnimator.setTarget(yanfa);
        objectAnimator.start();

        Animator objectAnimator1 = AnimatorInflater.loadAnimator(getActivity(),R.animator.card_shake);
        objectAnimator1.setTarget(android);
        objectAnimator1.start();

        Animator objectAnimator2 = AnimatorInflater.loadAnimator(getActivity(),R.animator.card_shake);
        objectAnimator2.setTarget(web);
        objectAnimator2.start();

        Animator objectAnimator3 = AnimatorInflater.loadAnimator(getActivity(),R.animator.card_shake);
        objectAnimator3.setTarget(java);
        objectAnimator3.start();

        Animator objectAnimator4 = AnimatorInflater.loadAnimator(getActivity(),R.animator.card_shake);
        objectAnimator4.setTarget(data);
        objectAnimator4.start();
    }


}
