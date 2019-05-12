package com.app.exceptions;

/**
 * This exception should be thrown when a not runnable task is tried to be run
 */
public class TaskNotRunnableException extends RuntimeException{
    public TaskNotRunnableException(String message) {
        super(message);
    }
}
