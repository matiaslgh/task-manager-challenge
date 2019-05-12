package com.app.exceptions;

/**
 * This exception should be thrown ONLY to simulate a failure
 */
public class FakeException extends Exception {

    public FakeException(String message) {
        super(message);
    }
}
