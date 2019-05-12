package com.app.exceptions;

/**
 * This exception should be thrown when a request is not valid
 */
public class BadRequestException extends RuntimeException {
    public BadRequestException(String message) {
        super(message);
    }
}
