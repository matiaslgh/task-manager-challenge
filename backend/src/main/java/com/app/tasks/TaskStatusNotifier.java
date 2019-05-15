package com.app.tasks;

import com.app.controllers.TaskDataController;
import com.app.tasks.models.TaskData;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Notifies to {@link TaskManager} and {@link TaskDataController} the changes of a task status
 */
@Component
public class TaskStatusNotifier {

    private static final Logger log = LogManager.getLogger(TaskStatusNotifier.class);

    @Autowired
    private TaskManager taskManager;

    @Autowired
    private TaskDataController taskDataController;

    public void notifyTaskCreation(TaskData taskData) {
        taskDataController.notifyViaWebSocket(taskData);
    }

    public void notifyTaskExecution(String id) {
        log.info("The task " + id + " is running");
        taskManager.setRunningStatus(id);
        taskDataController.notifyViaWebSocket(taskManager.getTask(id));
    }

    public void notifyTaskSuccess(String id, boolean result) {
        log.info("The task " + id + " has finished successfully");
        taskManager.setCompletedStatus(id, result);
        taskDataController.notifyViaWebSocket(taskManager.getTask(id));
    }

    public void notifyTaskFailure(String id, Exception e) {
        log.error("The task " + id + " has failed: " + e.getMessage());
        taskManager.setFailedStatus(id);
        taskDataController.notifyViaWebSocket(taskManager.getTask(id));
    }
}
