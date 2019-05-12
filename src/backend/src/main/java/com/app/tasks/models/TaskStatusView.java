package com.app.tasks.models;

import java.io.Serializable;

public class TaskStatusView implements Serializable {

    private long id;
    private String text;

    public TaskStatusView(long id, String text) {
        this.id = id;
        this.text = text;
    }

    public long getId() {
        return id;
    }

    public String getText() {
        return text;
    }
}
