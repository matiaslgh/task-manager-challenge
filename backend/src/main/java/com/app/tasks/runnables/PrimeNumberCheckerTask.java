package com.app.tasks.runnables;

import com.app.exceptions.FakeException;
import com.app.tasks.TaskManager;
import com.app.tasks.TaskStatusNotifier;
import org.springframework.beans.factory.annotation.Autowired;

import static com.app.utils.NumberUtils.isPrime;
import static com.app.utils.SimulationUtils.simulateFailure;

public class PrimeNumberCheckerTask implements Runnable {

    private static final long PROCESSING_TIME_IN_MILLIS = 60000L;
    private static final float FAILURE_RATE = 0.2F;

    private String id;
    private int input;
    private TaskStatusNotifier taskStatusNotifier;

    @Autowired
    private TaskManager taskManager;

    public PrimeNumberCheckerTask(String id, int input, TaskStatusNotifier taskStatusNotifier) {
        this.id = id;
        this.input = input;
        this.taskStatusNotifier = taskStatusNotifier;
    }

    /**
     * Verifies if a number is prime and notifies the statuses
     */
    @Override
    public void run() {
        try {
            taskStatusNotifier.notifyTaskExecution(id);

            Thread.sleep(PROCESSING_TIME_IN_MILLIS);

            simulateFailure(FAILURE_RATE);

            taskStatusNotifier.notifyTaskSuccess(id, isPrime(input));
        } catch (FakeException | InterruptedException e) {
            taskStatusNotifier.notifyTaskFailure(id, e);
        }
    }

}
