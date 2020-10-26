package com.wyzant;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import java.util.ArrayList;

public class ToDoListFragment extends Fragment implements View.OnClickListener, TaskView.OnDeleteTaskListener {

    private LinearLayout mLinearLayout;
    private TaskFragmentCoordinator mTaskFragmentCoordinator;

    public ToDoListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(getActivity() instanceof TaskFragmentCoordinator) {
            mTaskFragmentCoordinator = (TaskFragmentCoordinator)getActivity();
        } else {
            throw new IllegalStateException("Activity has to conform to ToDoCoordinator");
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_to_do_list, container, false);
        mLinearLayout = view.findViewById(R.id.linearLayout);
        return view;
    }

    public void updateList(ArrayList<Task> items) {
        mLinearLayout.removeAllViews();
        for(Task item : items) {
            TaskView taskView = new TaskView(getContext(), item);
            taskView.setText(item.getName());
            mLinearLayout.addView(taskView);
            taskView.setOnClickListener(this);
            taskView.setOnDeleteListener(this); // To be notified when a task is deleted.
        }
    }

    @Override
    public void onClick(View view) {
        TaskView taskView = ((TaskView)view);
        taskView.toggleDone();
        mTaskFragmentCoordinator.taskCompletenessWasToggled(taskView.getTask());
    }


    @Override
    public void taskDeleted(TaskView taskView) {
        mLinearLayout.removeView(taskView); // Physically removes the view
        mTaskFragmentCoordinator.taskWasRemoved(taskView.getTask()); // Passes the message the coordinator to remove it from the phone's memory
    }
}