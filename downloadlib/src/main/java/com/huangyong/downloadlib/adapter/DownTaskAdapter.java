package com.huangyong.downloadlib.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.huangyong.downloadlib.R;
import com.huangyong.downloadlib.domain.DowningTaskInfo;

import java.util.ArrayList;
import java.util.List;

public class DownTaskAdapter extends RecyclerView.Adapter<TaskHolder> {

    private Context context;
    private List<DowningTaskInfo> taskInfo = new ArrayList();
    @NonNull
    @Override
    public TaskHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_task_layout,null);
        return new TaskHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskHolder holder, int position) {

        holder.posterImg.setProgress(30);
        holder.posterImg.setImageResource(R.drawable.poster);
        holder.taskTitile.setText(taskInfo.get(position).getTitle());
        Glide.with(context).load(taskInfo.get(position).getPostImgUrl()).into(holder.posterImg);
        int received = Integer.parseInt(taskInfo.get(position).getReceiveSize());
        int total = Integer.parseInt(taskInfo.get(position).getTotalSize());
//        holder.posterImg.setProgress((int) (received*1.0f/total*1.0f*100));
    }

    @Override
    public int getItemCount() {
        return taskInfo.size();
    }

    public void setTaskData(List<DowningTaskInfo> info) {
        this.taskInfo.clear();
        this.taskInfo.addAll(info);
    }
}
