package com.app.exceptions;

/**
 * This exception should be thrown when a resource is not found
 */
public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {
        super(message);
    }
}
