package com.example.yanfa.ui.fragment;



import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.yanfa.R;
import com.example.yanfa.ui.activity.DetailActivity;

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


        view.findViewById(R.id.imageView_cardView_yanfa).setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), DetailActivity.class);
            intent.putExtra("type",YANFA);
            startAct(intent);

        });

        view.findViewById(R.id.imageView_cardView_java).setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), DetailActivity.class);
            intent.putExtra("type",JAVA);
            startAct(intent);
        });

        view.findViewById(R.id.imageView_cardView_web ).setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), DetailActivity.class);
            intent.putExtra("type",WEB);
            startAct(intent);
        });

        view.findViewById(R.id.imageView_cardView_android).setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), DetailActivity.class);
            intent.putExtra("type",ANDROID);
            startAct(intent);
        });

        view.findViewById(R.id.imageView_cardView_data).setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), DetailActivity.class);
            intent.putExtra("type",DATA);
            startAct(intent);
        });
    }

    private void startAct(Intent intent){
        startActivityForResult(intent,1);
        if (getActivity()!=null)
        getActivity().overridePendingTransition(R.anim.activity_start_anim,R.anim.activity_finish_anim);
    }




}
