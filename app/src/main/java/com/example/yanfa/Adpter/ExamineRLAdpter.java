package com.example.yanfa.Adpter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yanfa.R;
import com.example.yanfa.bean.ExamineInfo;

import java.util.List;

public class ExamineRLAdpter extends RecyclerView.Adapter<ExamineRLAdpter.MyHolder> {
    Context context;
    List<ExamineInfo> list;

    public ExamineRLAdpter (Context context,List<ExamineInfo> list){
        this.context = context;
        this.list = list;
    }

    public void update(List<ExamineInfo> list){
        this.list = list;
        notifyDataSetChanged();
    }

    class MyHolder extends  RecyclerView.ViewHolder{

        TextView tvStage,tvMsg;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            tvStage = itemView.findViewById(R.id.tv_stage);
            tvMsg = itemView.findViewById(R.id.tv_msg);
        }
    }


    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.examine_rl_item,null);
        MyHolder myHolder = new MyHolder(view);
        return myHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        String s = list.get(position).getStage();
        String m = list.get(position).getMsg();
        holder.tvStage.setText(s);
        holder.tvMsg.setText(m);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
