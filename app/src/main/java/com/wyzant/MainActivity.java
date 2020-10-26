package com.wyzant;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity implements TaskFragmentCoordinator {

    private ToDoManager mToDoManager;
    private AddToDoFragment mAddToDoFragment;
    private ToDoListFragment mToDoListFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAddToDoFragment = (AddToDoFragment)getSupportFragmentManager().findFragmentById(R.id.addToDoFragment);
        mToDoListFragment = (ToDoListFragment)getSupportFragmentManager().findFragmentById(R.id.toDoListFragment);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mToDoManager = ToDoManager.getInstance(this);
        mToDoListFragment.updateList(mToDoManager.getToDoItems());
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }


    @Override
    protected void onResume() {
        super.onResume();
    }


    @Override
    protected void onStop() {
        super.onStop();
        Log.d("MAIN_ACTIVITY", "onStop()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("MAIN_ACTIVITY", "onPause()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void taskWasAdded(@NonNull String name) {
        Task item = new Task(name);
        mToDoManager.addNewTask(item);
        mToDoListFragment.updateList(mToDoManager.getToDoItems());
    }

    @Override
    public void taskWasRemoved(Task item) {
        mToDoManager.removeTask(item.getId());
        mToDoListFragment.updateList(mToDoManager.getToDoItems());
    }

    @Override
    public void taskCompletenessWasToggled(Task item) {
        mToDoManager.markTaskAsDone(item.getId(), item.isDone());
    }
}