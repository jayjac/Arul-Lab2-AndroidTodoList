package com.wyzant;

import com.google.gson.Gson;

import org.junit.Test;

public class ToDoUnitTest {

    @Test
    public void todo_item_converted() {
        Task item = new Task("Laundry");
        Gson gson = new Gson();
        String json = gson.toJson(item);
        return;

    }
}
