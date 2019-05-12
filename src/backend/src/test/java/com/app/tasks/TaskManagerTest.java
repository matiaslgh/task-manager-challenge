package com.app.tasks;

import com.app.exceptions.NotFoundException;
import com.app.tasks.models.TaskData;
import com.app.tasks.models.TaskStatusView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;
import java.util.stream.Collectors;

import static com.app.tasks.models.TaskStatus.*;
import static org.junit.jupiter.api.Assertions.*;

@RunWith(MockitoJUnitRunner.class)
public class TaskManagerTest {

    private static final int NUMBER = 5;
    private static final String STRING = "STRING";

    private TaskManager taskManager;

    private TaskStatusNotifier taskStatusNotifier;

    @BeforeEach
    public void setup() {
        taskManager = new TaskManager();
        taskStatusNotifier = Mockito.mock(TaskStatusNotifier.class);
    }

    @Test
    public void testTaskIsCreated() {
        taskManager.setTaskStatusNotifier(taskStatusNotifier);

        TaskData taskData = taskManager.createTask(NUMBER);
        String id = taskData.getId();
        assertEquals(id, taskManager.getTask(id).getId());
    }

    @Test
    public void testGetTaskThrowsExceptionIfItWasNotFound() {
        assertThrows(NotFoundException.class, () -> taskManager.getTask(STRING));
    }

    @Test
    public void testGetStatuses() {
        List<Long> list = taskManager.getStatuses().stream().map(TaskStatusView::getId).collect(Collectors.toList());
        assertAll(() -> {
            assertTrue(list.contains(WAITING_FOR_RUNNING.getId()));
            assertTrue(list.contains(RUNNING.getId()));
            assertTrue(list.contains(COMPLETED.getId()));
            assertTrue(list.contains(FAILED.getId()));
        });
    }
}