package com.wyzant;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;


import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

/**
 * This fragment consists of the title, the editText and the add button.
 */
public class AddToDoFragment extends Fragment implements OnClickListener {

    private Button addButton;
    private EditText toDoNameText;

    private TaskFragmentCoordinator mTaskFragmentCoordinator;

    public AddToDoFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_to_do, container, false);

        addButton = view.findViewById(R.id.addToDoButton);
        toDoNameText = view.findViewById(R.id.toDoNameField);
//
        addButton.setOnClickListener(this);
        return view;
    }



    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        // Now we know we have an activity attached to us so we can get a reference to it
        // This activity has to be a ToDoCoordinator
        if(getActivity() instanceof TaskFragmentCoordinator) {
            mTaskFragmentCoordinator = (TaskFragmentCoordinator) getActivity();
        } else {
            throw new IllegalStateException("Activity has to conform to ToDoCoordinator");
        }
    }



    @Override
    public void onClick(View view) {
        String todoName = toDoNameText.getText().toString().trim();
        if(todoName != null && !todoName.isEmpty()) {
            toDoNameText.setText("");
            mTaskFragmentCoordinator.taskWasAdded(todoName);
        }
    }
}