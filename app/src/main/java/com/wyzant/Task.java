package com.wyzant;

import com.google.gson.Gson;

import java.util.Date;
/**
 * This class is a model class that holds data regarding a single task.
 */
public class Task {

    private String name;
    private boolean done;
    private Date createdAt;
    private long id;

    public Task(String name) {
        this.name = name;
        this.done = false;
        Date now = new Date();
        this.createdAt = now;
        id = now.getTime();
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public Date getCreatedAt() {
        return createdAt;
    }



}
