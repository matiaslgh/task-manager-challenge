package com.app.tasks.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TaskDataTest {

    private static final int NUMBER = 5;

    @Test
    void testTaskDataIsWaitingForRunningWhenInstancing() {
        TaskData taskData = new TaskData(NUMBER);
        assertEquals(TaskStatus.WAITING_FOR_RUNNING.getId(), taskData.getStatusId());
    }
}