package com.app.tasks;

import com.app.exceptions.NotFoundException;
import com.app.exceptions.TaskNotRunnableException;
import com.app.exceptions.TooManyTasksRunningException;
import com.app.tasks.models.TaskData;
import com.app.tasks.models.TaskStatus;
import com.app.tasks.models.TaskStatusView;
import com.app.tasks.runnables.PrimeNumberCheckerTask;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

import static com.app.tasks.models.TaskStatus.COMPLETED;
import static com.app.tasks.models.TaskStatus.FAILED;
import static com.app.tasks.models.TaskStatus.RUNNING;

/**
 * Manages the {@link TaskData} tasks (creation, statuses changing, excecution, etc)
 */
@Component
public class TaskManager {

    private TaskStatusNotifier taskStatusNotifier;

    private static final Logger log = LogManager.getLogger(TaskManager.class);
    private static final int MAX_NUMBER_OF_TASK_RUNNING = 3;

    private Map<String, TaskData> tasks = new HashMap<>();
    private int runningCount = 0;
    private ExecutorService executorService;

    TaskManager() {
        this.executorService = Executors.newFixedThreadPool(MAX_NUMBER_OF_TASK_RUNNING);
    }

    @PreDestroy
    public void cleanUp() throws Exception {
        this.executorService.shutdown();
    }

    public TaskData createTask(int number) {
        TaskData taskData = new TaskData(number);
        tasks.put(taskData.getId(), taskData);
        taskStatusNotifier.notifyTaskCreation(taskData);
        log.info("The task " + taskData.getId() + " has been created");
        return taskData;
    }

    public void runTask(String id) {
        if (runningCount < MAX_NUMBER_OF_TASK_RUNNING) {
            TaskData taskData = getTask(id);
            if (taskData.getStatus().isRunnable()) {
                executorService.submit(new Thread(new PrimeNumberCheckerTask(id, taskData.getInput(), taskStatusNotifier)));
            } else {
                log.info("The task " + id + " with status: " + taskData.getStatusText() + " was tried to be run");
                throw  new TaskNotRunnableException("The task " + id + " cannot be run");
            }
        } else {
            log.info("Cannot run one more task, the limit was reached");
            throw new TooManyTasksRunningException("Only can be " + MAX_NUMBER_OF_TASK_RUNNING + " tasks running at the same time");
        }
    }

    public List<TaskData> getTasks() {
        return new ArrayList<>(tasks.values());
    }

    public TaskData getTask(String id) {
        TaskData taskData = tasks.get(id);
        if (taskData == null) {
            throw new NotFoundException("Task " + id + " not found");
        }
        return taskData;
    }

    public List<TaskStatusView> getStatuses() {
        return Arrays.stream(TaskStatus.values())
                .map(s -> new TaskStatusView(s.getId(), s.getText()))
                .collect(Collectors.toList());
    }

    public void setRunningStatus(String id) {
        TaskData taskData = getTask(id);
        taskData.setStatus(RUNNING);
        taskData.setExecutionDate(new Date());
        runningCount++;
    }

    public void setCompletedStatus(String id, boolean result) {
        TaskData taskData = getTask(id);
        taskData.setStatus(COMPLETED);
        taskData.setFinishDate(new Date());
        taskData.setResult(result);
        runningCount--;
    }

    public void setFailedStatus(String id) {
        TaskData taskData = getTask(id);
        taskData.setStatus(FAILED);
        taskData.setFinishDate(new Date());
        runningCount--;
    }

    @Autowired
    public void setTaskStatusNotifier(TaskStatusNotifier taskStatusNotifier) {
        this.taskStatusNotifier = taskStatusNotifier;
    }
}
