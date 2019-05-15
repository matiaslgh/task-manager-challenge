package com.app.tasks.models;

import java.util.Date;
import java.util.UUID;

import static com.app.tasks.models.TaskStatus.WAITING_FOR_RUNNING;

public class TaskData {

    private String id;
    private int input;
    private TaskStatus status;
    private boolean resultVisible;
    private boolean runnable;
    private Date creationDate;
    private Date executionDate;
    private Date finishDate;
    private boolean result;


    public TaskData(int input) {
        this.id = UUID.randomUUID().toString();
        setStatus(WAITING_FOR_RUNNING);
        this.creationDate = new Date();
        this.input = input;
    }

    public String getId() {
        return id;
    }

    public int getInput() {
        return input;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public String getStatusText() {
        return status.getText();
    }

    public long getStatusId() {
        return status.getId();
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
        this.resultVisible = status.isResultVisible();
        this.runnable = status.isRunnable();
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public Date getExecutionDate() {
        return executionDate;
    }

    public void setExecutionDate(Date executionDate) {
        this.executionDate = executionDate;
    }

    public Date getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(Date finishDate) {
        this.finishDate = finishDate;
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public boolean isResultVisible() {
        return resultVisible;
    }

    public boolean isRunnable() {
        return runnable;
    }
}
