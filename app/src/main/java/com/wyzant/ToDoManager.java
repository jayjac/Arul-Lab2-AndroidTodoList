package com.wyzant;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;

public class ToDoManager {



    private final static String SHARED_PREFERENCES = "TO_DO_LIST";
    private final static String TODO_ITEMS_KEY = "TO_DO_ITEMS";
    private static ToDoManager singleInstance;
    private ArrayList<Task> mToDoItems = new ArrayList<>();
    private Context mContext;

    public static ToDoManager getInstance(Context context) {
        if(singleInstance == null) {
            singleInstance = new ToDoManager(context);
        }
        return singleInstance;
    }

    private ToDoManager(Context context) {
        mContext = context;
        fetchTasksFromStorage();
    }

    public ArrayList<Task> getToDoItems() {
        return mToDoItems;
    }

    public void addNewTask(Task task) {
        SharedPreferences preferences = mContext.getSharedPreferences(SHARED_PREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        mToDoItems.add(task);
        Gson gson = new Gson();
        editor.putString(TODO_ITEMS_KEY, gson.toJson(mToDoItems));
        editor.apply();
    }

    /**
     * Removes a Task from storage
     * @param id the Task's identifier
     * @return
     */
    public void removeTask(long id) {
        SharedPreferences preferences = mContext.getSharedPreferences(SHARED_PREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        boolean itemWasRemoved = false;
        for(int i = 0; i < mToDoItems.size(); i++) {
            Task item = mToDoItems.get(i);
            if(item.getId() == id) {
                mToDoItems.remove(i);
                itemWasRemoved = true;
                break;
            }
        }
        if(itemWasRemoved) {
            Gson gson = new Gson();
            editor.putString(TODO_ITEMS_KEY, gson.toJson(mToDoItems));
            editor.commit();
        }
    }

    public void markTaskAsDone(long id, boolean done) {
        for(int i = 0; i < mToDoItems.size(); i++) {
            Task item = mToDoItems.get(i);
            if(item.getId() == id) {
                item.setDone(done);
                break;
            }
        }
        SharedPreferences preferences = mContext.getSharedPreferences(SHARED_PREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        Gson gson = new Gson();
        editor.putString(TODO_ITEMS_KEY, gson.toJson(mToDoItems));
        editor.apply();
    }

    private ArrayList<Task> fetchTasksFromStorage() {
        SharedPreferences preferences = mContext.getSharedPreferences(SHARED_PREFERENCES, Context.MODE_PRIVATE);
        String tasksAsString = preferences.getString(TODO_ITEMS_KEY, "[]");
        Gson gson = new Gson();
        Task[] items = gson.fromJson(tasksAsString, Task[].class);
        mToDoItems = new ArrayList<>(Arrays.asList(items));
        return mToDoItems;
    }

}
