package com.pattash.udhaya.mytodo.adapter;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.pattash.udhaya.mytodo.R;
import com.pattash.udhaya.mytodo.model.Task;

import java.util.ArrayList;

/**
 * Created by Udhay on 7/20/2016.
 */
public class TaskAdapter extends BaseAdapter {

    private ArrayList<Task> mTaskArrayList;
    private Context mContext;

    private LayoutInflater mLayoutInflater;

    public TaskAdapter(Context mContext, ArrayList<Task> mTaskArrayList) {
        this.mContext = mContext;
        this.mTaskArrayList = mTaskArrayList;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    public void setTaskList (ArrayList<Task> taskList) {
        this.mTaskArrayList = taskList;
    }

    @Override
    public int getCount() {
        return mTaskArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return mTaskArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = mLayoutInflater.inflate(R.layout.task_list, parent, false);

        TextView header = (TextView) convertView.findViewById(R.id.header);
        TextView title = (TextView) convertView.findViewById(R.id.taskTitle);
        TextView description = (TextView) convertView.findViewById(R.id.taskDescription);
        TextView targetDate = (TextView) convertView.findViewById(R.id.targetDate);

        ImageView imageView = (ImageView) convertView.findViewById(R.id.imageView);

        Task theTask = mTaskArrayList.get(position);
        header.setText(theTask.getTargetDate());
        title.setText(theTask.getTaskTitle());
        description.setText(theTask.getTaskDescription());
        targetDate.setText(theTask.getTargetDate());

        if (theTask.getStatus() == 1)
            imageView.setImageResource(R.drawable.thumbsup_white);
        else
            imageView.setImageResource(R.drawable.thumbsup_blue);

        return convertView;
    }
}
