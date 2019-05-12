package com.app.tasks.models;

public enum TaskStatus {
    WAITING_FOR_RUNNING(1, "Waiting for running", false, true),
    RUNNING(2, "Running", false, false),
    COMPLETED(3, "Finished", true, false),
    FAILED(4, "Failed", false, false);

    private long id;
    private String text;
    private boolean resultVisible;
    private boolean runnable;

    TaskStatus(long id, String text, boolean resultVisible,boolean runnable ) {
        this.id = id;
        this.text = text;
        this.resultVisible = resultVisible;
        this.runnable = runnable;
    }

    public long getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public boolean isResultVisible() {
        return resultVisible;
    }

    public boolean isRunnable() {
        return runnable;
    }
}
