package com.app.exceptions;

/**
 * This exception should be thrown when the limit of requests is tried to be overcome
 */
public class TooManyTasksRunningException extends RuntimeException {
    public TooManyTasksRunningException(String message) {
        super(message);
    }
}
