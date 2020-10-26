package com.wyzant;

import android.content.Context;

import android.graphics.Paint;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;


/**
 * This is a custom view that holds the red DELETE button to the right.
 * The layout is defined in the task_view.xml layout file.
 */
public class TaskView extends LinearLayout implements View.OnClickListener {

    private TextView mTextView;
    private Task mTask;
    private Button mDeleteButton;
    private OnDeleteTaskListener mOnDeleteTaskListener;

    /**
     * This interface is to notify when an item has been deleted.
     * Here the ToDoList fragment implements this interface.
     */
    interface OnDeleteTaskListener {
        void taskDeleted(TaskView view);
    }

    public TaskView(Context context, Task task) {
        super(context);
        mTask = task;
        inflate(context, R.layout.task_view, this);
        mTextView = findViewById(R.id.textView);
        mDeleteButton = findViewById(R.id.deleteButton);
        mDeleteButton.setOnClickListener(this);
        setText(task.getName());
        strikeThroughWhenComplete();
    }

    public void setText(String text) {
        mTextView.setText(text);
    }

    public void setOnDeleteListener(OnDeleteTaskListener listener) {
        mOnDeleteTaskListener = listener;
    }



    public Task getTask() {
        return mTask;
    }

    public void toggleDone() {
        boolean done = mTask.isDone();
        mTask.setDone(!done);
        strikeThroughWhenComplete();
    }

    /**
     * Crosses off the task when it is complete, and makes it slightly transparent.
     */
    private void strikeThroughWhenComplete() {
        if(mTask.isDone()) {
            mTextView.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
            mTextView.setAlpha(0.4f);
        } else {
            mTextView.setPaintFlags(0);
            mTextView.setAlpha(1f);
        }
    }

    @Override
    public void onClick(View view) {
        if(mOnDeleteTaskListener != null) {
            mOnDeleteTaskListener.taskDeleted(this);
        }
    }


}
