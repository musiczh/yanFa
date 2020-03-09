package com.example.yanfa.ui.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.yanfa.Adpter.ExamineRLAdpter;
import com.example.yanfa.MainActivity;
import com.example.yanfa.R;
import com.example.yanfa.bean.ExamineInfo;
import com.example.yanfa.bean.NoticBean;
import com.example.yanfa.bean.PhaseBean;
import com.example.yanfa.iApiService.NoticSecApiService;
import com.example.yanfa.iApiService.PhaseApiService;
import com.example.yanfa.util.ExamineInfoDTL;
import com.example.yanfa.util.RetrofitManager;
import com.orient.me.widget.rv.itemdocration.timeline.TimeLine;
import com.orient.me.widget.rv.layoutmanager.doubleside.DoubleSideLayoutManager;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * 考核fragment
 */
public class ExamineFragment extends Fragment {

    private RecyclerView rlExamine;
    private ExamineRLAdpter exAdpter;
    ArrayList<ExamineInfo> data = new ArrayList<>();
    NoticBean noticBean;
    int code = 0;
    private MainActivity mainActivity;
    View view;
    TimeLine timeLine;
    SwipeRefreshLayout mRefreshLayout;
    String notic;
    AlertDialog.Builder cc;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_examine, container, false);
        getNot();
        addData();
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
        getNot();
        reflashRL();
    }

    private void initView(View view) {

//        mainActivity = (MainActivity) getActivity();
//        if(!mainActivity.getIfLogin()){
//            dialogBox2();      //若没有登录则先登录
//        }
        rlExamine = view.findViewById(R.id.rl_examine);
        rlExamine.setLayoutManager(new DoubleSideLayoutManager(DoubleSideLayoutManager.START_LEFT));
        exAdpter = new ExamineRLAdpter(getContext(),data);
        rlExamine.setAdapter(exAdpter);
        timeLine = provideTimeLine(data);
        rlExamine.addItemDecoration(timeLine);
        exAdpter.setOnItemClickListener(new ExamineRLAdpter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {

                dialogBox();
            }
        });
        mRefreshLayout = view.findViewById(R.id.layout_swipe_refresh);
        mRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getNot();
                reflashRL();
                mRefreshLayout.setRefreshing(false);
            }
        });
    }

    private void addData() {

                data.add(new ExamineInfo("笔试","通过", Color.parseColor("#238E23"),
                        R.drawable.ex_pan,false));
                data.add(new ExamineInfo("一轮面试","未开放", Color.parseColor("#2F4F4F"),
                        R.drawable.ex_face,false));
                data.add(new ExamineInfo("二轮面试","未开放", Color.parseColor("#2F4F4F"),
                        R.drawable.ex_face,false));
                data.add(new ExamineInfo("一轮考核","未开放", Color.parseColor("#2F4F4F"),
                        R.drawable.ex_test,false));
                data.add(new ExamineInfo("二轮考核","未开放", Color.parseColor("#2F4F4F"),
                        R.drawable.ex_test,false));
                reflashRL();
            }

    private void reflashRL(){
    RetrofitManager.getInstance().createRs(PhaseApiService.class)
            .getPhase()
            .enqueue(new Callback<PhaseBean>() {
                @Override
                public void onResponse(Call<PhaseBean> call, Response<PhaseBean> response) {
                    if (response.body()!=null){
                        code = response.body().getData();
                        switch (code) {
                            case 11:
                                data.set(0, new ExamineInfo("笔试", "通过", Color.parseColor("#238E23"),
                                        R.drawable.ex_pan,false));
                                data.set(1, new ExamineInfo("一轮面试", "进行中", Color.parseColor("#FFFF00"),
                                        R.drawable.ex_face,true));
                                data.set(2, new ExamineInfo("二轮面试", "未开放", Color.parseColor("#2F4F4F"),
                                        R.drawable.ex_face,false));
                                data.set(3, new ExamineInfo("一轮考核", "未开放", Color.parseColor("#2F4F4F"),
                                        R.drawable.ex_test,false));
                                data.set(4, new ExamineInfo("二轮考核", "未开放", Color.parseColor("#2F4F4F"),
                                        R.drawable.ex_test,false));
                                break;
                            case 10:
                                data.set(0, new ExamineInfo("笔试", "不通过", Color.parseColor("#f36c60"),
                                        R.drawable.ex_pan,true));
                                data.set(1, new ExamineInfo("一轮面试", "未开放", Color.parseColor("#2F4F4F"),
                                        R.drawable.ex_face,false));
                                data.set(2, new ExamineInfo("二轮面试", "未开放", Color.parseColor("#2F4F4F"),
                                        R.drawable.ex_face,false));
                                data.set(3, new ExamineInfo("一轮考核", "未开放", Color.parseColor("#2F4F4F"),
                                        R.drawable.ex_test,false));
                                data.set(4, new ExamineInfo("二轮考核", "未开放", Color.parseColor("#2F4F4F"),
                                        R.drawable.ex_test,false));
                                break;
                            case 21:
                                data.set(0, new ExamineInfo("笔试", "通过", Color.parseColor("#238E23"),
                                        R.drawable.ex_pan,false));
                                data.set(1, new ExamineInfo("一轮面试", "通过", Color.parseColor("#238E23"),
                                        R.drawable.ex_face,false));
                                data.set(2, new ExamineInfo("二轮面试", "进行中", Color.parseColor("#FFFF00"),
                                        R.drawable.ex_face,true));
                                data.set(3, new ExamineInfo("一轮考核", "未开放", Color.parseColor("#2F4F4F"),
                                        R.drawable.ex_test,false));
                                data.set(4, new ExamineInfo("二轮考核", "未开放", Color.parseColor("#2F4F4F"),
                                        R.drawable.ex_test,false));
                                break;
                            case 20:
                                data.set(0, new ExamineInfo("笔试", "通过", Color.parseColor("#238E23"),
                                        R.drawable.ex_pan,false));
                                data.set(1, new ExamineInfo("一轮面试", "不通过", Color.parseColor("#f36c60"),
                                        R.drawable.ex_face,true));
                                data.set(2, new ExamineInfo("二轮面试", "未开放", Color.parseColor("#2F4F4F"),
                                        R.drawable.ex_face,false));
                                data.set(3, new ExamineInfo("一轮考核", "未开放", Color.parseColor("#2F4F4F"),
                                        R.drawable.ex_test,false));
                                data.set(4, new ExamineInfo("二轮考核", "未开放", Color.parseColor("#2F4F4F"),
                                        R.drawable.ex_test,false));
                                break;
                            case 31:
                                data.set(0, new ExamineInfo("笔试", "通过", Color.parseColor("#238E23"),
                                        R.drawable.ex_pan,false));
                                data.set(1, new ExamineInfo("一轮面试", "通过", Color.parseColor("#238E23"),
                                        R.drawable.ex_face,false));
                                data.set(2, new ExamineInfo("二轮面试", "通过", Color.parseColor("#238E23"),
                                        R.drawable.ex_face,false));
                                data.set(3, new ExamineInfo("一轮考核", "进行中", Color.parseColor("#FFFF00"),
                                        R.drawable.ex_test,true));
                                data.set(4, new ExamineInfo("二轮考核", "未开放", Color.parseColor("#2F4F4F"),
                                        R.drawable.ex_test,false));
                                break;
                            case 30:
                                data.set(0, new ExamineInfo("笔试", "通过", Color.parseColor("#238E23"),
                                        R.drawable.ex_pan,false));
                                data.set(1, new ExamineInfo("一轮面试", "通过", Color.parseColor("#238E23"),
                                        R.drawable.ex_face,false));
                                data.set(2, new ExamineInfo("二轮面试", "不通过", Color.parseColor("#f36c60"),
                                        R.drawable.ex_face,true));
                                data.set(3, new ExamineInfo("一轮考核", "未开放", Color.parseColor("#2F4F4F"),
                                        R.drawable.ex_test,false));
                                data.set(4, new ExamineInfo("二轮考核", "未开放", Color.parseColor("#2F4F4F"),
                                        R.drawable.ex_test,false));
                                break;
                            case 41:
                                data.set(0, new ExamineInfo("笔试", "通过", Color.parseColor("#238E23"),
                                        R.drawable.ex_pan,false));
                                data.set(1, new ExamineInfo("一轮面试", "通过", Color.parseColor("#238E23"),
                                        R.drawable.ex_face,false));
                                data.set(2, new ExamineInfo("二轮面试", "通过", Color.parseColor("#238E23"),
                                        R.drawable.ex_face,false));
                                data.set(3, new ExamineInfo("一轮考核", "通过", Color.parseColor("#238E23"),
                                        R.drawable.ex_test,false));
                                data.set(4, new ExamineInfo("二轮考核", "进行中", Color.parseColor("#FFFF00"),
                                        R.drawable.ex_test,true));
                                break;
                            case 40:
                                data.set(0, new ExamineInfo("笔试", "通过", Color.parseColor("#238E23"),
                                        R.drawable.ex_pan,false));
                                data.set(1, new ExamineInfo("一轮面试", "通过", Color.parseColor("#238E23"),
                                        R.drawable.ex_face,false));
                                data.set(2, new ExamineInfo("二轮面试", "通过", Color.parseColor("#238E23"),
                                        R.drawable.ex_face,false));
                                data.set(3, new ExamineInfo("一轮考核", "不通过", Color.parseColor("#f36c60"),
                                        R.drawable.ex_test,true));
                                data.set(4, new ExamineInfo("二轮考核", "未开放", Color.parseColor("#2F4F4F"),
                                        R.drawable.ex_test,false));
                                break;
                            case 51:
                                data.set(0, new ExamineInfo("笔试", "通过", Color.parseColor("#238E23"),
                                        R.drawable.ex_pan,false));
                                data.set(1, new ExamineInfo("一轮面试", "通过", Color.parseColor("#238E23"),
                                        R.drawable.ex_face,false));
                                data.set(2, new ExamineInfo("二轮面试", "通过", Color.parseColor("#238E23"),
                                        R.drawable.ex_face,false));
                                data.set(3, new ExamineInfo("一轮考核", "进行中", Color.parseColor("#FFFF00"),
                                        R.drawable.ex_test,false));
                                data.set(4, new ExamineInfo("二轮考核", "通过", Color.parseColor("#FFFF00"),
                                        R.drawable.ex_test,true));
                                break;
                            case 50:
                                data.set(0, new ExamineInfo("笔试", "通过", Color.parseColor("#238E23"),
                                        R.drawable.ex_pan,false));
                                data.set(1, new ExamineInfo("一轮面试", "通过", Color.parseColor("#238E23"),
                                        R.drawable.ex_face,false));
                                data.set(2, new ExamineInfo("二轮面试", "通过", Color.parseColor("#238E23"),
                                        R.drawable.ex_face,false));
                                data.set(3, new ExamineInfo("一轮考核", "通过", Color.parseColor("#238E23"),
                                        R.drawable.ex_test,false));
                                data.set(4, new ExamineInfo("二轮考核", "不通过", Color.parseColor("#f36c60"),
                                        R.drawable.ex_test,true));
                                break;
                            default:
                                break;
                        }
                        exAdpter.update(data);
                        timeLine.replace(data);
                    }
                }

                @Override
                public void onFailure(Call<PhaseBean> call, Throwable t) {

                }
            });
}

    private void getNot(){
        RetrofitManager.getInstance().createRs(NoticSecApiService.class)
                .getNoticeSec()
                .enqueue(new Callback<NoticBean>() {
                    @Override
                    public void onResponse(Call<NoticBean> call, Response<NoticBean> response) {
                        if(response.body() != null) {
                            notic = response.body().getData();
                        }
                    }

                    @Override
                    public void onFailure(Call<NoticBean> call, Throwable t) {
                            notic = "获取失败！";
                    }
                });
    }



    private TimeLine provideTimeLine(List<ExamineInfo> timeItems) {
        return new TimeLine.Builder(getContext(), timeItems)
                .setTitleStyle(TimeLine.FLAG_TITLE_TYPE_LEFT, 0)
                .setLine(TimeLine.FLAG_LINE_BEGIN_TO_END, 60, Color.parseColor("#757575"),3)
                .setDot(TimeLine.FLAG_DOT_RES)
                .build(ExamineInfoDTL.class);
    }
    private void dialogBox2() {
        AlertDialog.Builder bb = new AlertDialog.Builder(getContext());
        bb.setMessage("请先登录");
        bb.setTitle("提示");
        bb.setCancelable(false);
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
    private void dialogBox() {
        cc = new AlertDialog.Builder(getContext());
        cc.setMessage(notic);
        cc.setTitle("详情");
        cc.setCancelable(true);
        cc.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                    cc.setCancelable(true);
            }
        });

        cc.show();
    }
}
