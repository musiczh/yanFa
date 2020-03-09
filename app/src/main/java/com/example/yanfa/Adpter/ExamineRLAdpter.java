package com.example.yanfa.Adpter;

import android.content.Context;
import android.provider.ContactsContract;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yanfa.R;
import com.example.yanfa.bean.ExamineInfo;

import java.util.ArrayList;
import java.util.List;

public class ExamineRLAdpter extends RecyclerView.Adapter<ExamineRLAdpter.MyHolder> {
    Context context;
    ArrayList<ExamineInfo> list;

    public ExamineRLAdpter (Context context,ArrayList<ExamineInfo> list){
        this.context = context;
        this.list = list;
    }

    public void update(ArrayList<ExamineInfo> list){
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
    public void onBindViewHolder(@NonNull MyHolder holder, final int position) {
        String s = list.get(position).getStage();
        String m = list.get(position).getMsg();
        holder.tvStage.setText(s);
        holder.tvMsg.setText(m);
        if(list.get(position).getBoolean()){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onItemClickListener != null) {
                        onItemClickListener.onItemClick(v, position);
                    }
                }
            });
        }else {
            holder.itemView.setOnClickListener(null);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    //私有属性
    private OnItemClickListener onItemClickListener = null;

    //setter方法
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    //回调接口
    public interface OnItemClickListener {
        void onItemClick(View v, int position);
    }
}
