package com.pattash.udhaya.mytodo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.pattash.udhaya.mytodo.adapter.TaskAdapter;
import com.pattash.udhaya.mytodo.dbhelper.DBHelper;
import com.pattash.udhaya.mytodo.extras.DateComparator;
import com.pattash.udhaya.mytodo.model.Task;

import java.util.ArrayList;
import java.util.Collections;

public class CompletedTaskActivity extends AppCompatActivity implements AdapterView.OnItemLongClickListener {

    ListView listView;
    Button mAdd;

    // For Custom ListView
    ArrayList<Task> taskArrayList;
    TaskAdapter taskArrayAdapter;

    // For Dialog Creation
    LayoutInflater mLayoutInflater;

    // For DB
    DBHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // For Dialog
        mLayoutInflater = LayoutInflater.from(this);

        // For DB
        dbHelper = new DBHelper(this);

        // Creating list view to display
        listView = (ListView) findViewById(R.id.listView);

        taskArrayList = dbHelper.getAllCompletedTasks();
        Collections.sort(taskArrayList, new DateComparator());
        taskArrayAdapter = new TaskAdapter(this, taskArrayList);

        listView.setAdapter(taskArrayAdapter);

        listView.setOnItemLongClickListener(this);
    }

    /**
     * Method to refresh the contents of the List view
     */
    private void refreshListViewContents() {
        taskArrayList = dbHelper.getAllCompletedTasks();
        taskArrayAdapter.setTaskList(taskArrayList);
        taskArrayAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        // Should mark the given one as completed
        Task theTask = (Task) parent.getAdapter().getItem(position);

        boolean isDeleted = dbHelper.deleteTask(theTask.getId());

        if (isDeleted)
        {
            refreshListViewContents();
            Toast.makeText(this, "Removed the completed task : " + theTask.getTaskTitle(), Toast.LENGTH_LONG).show();
        }

        return true;
    }
}
