package com.app.controllers.errors;

import com.app.exceptions.BadRequestException;
import com.app.exceptions.NotFoundException;
import com.app.exceptions.TaskNotRunnableException;
import com.app.exceptions.TooManyTasksRunningException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Handles different exceptions, then returns a ResponseEntity with the correct HttpStatus and a message
 */
@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = TooManyTasksRunningException.class)
    protected ResponseEntity<Object> handleTooManyTasksRunningException(RuntimeException ex) {
        return handleConflict(HttpStatus.TOO_MANY_REQUESTS, ex);
    }

    @ExceptionHandler(value = BadRequestException.class)
    protected ResponseEntity<Object> handleBadRequestException(RuntimeException ex) {
        return handleConflict(HttpStatus.BAD_REQUEST, ex);
    }

    @ExceptionHandler(value = NotFoundException.class)
    protected ResponseEntity<Object> handleNotFoundException(RuntimeException ex) {
        return handleConflict(HttpStatus.NOT_FOUND, ex);
    }

    @ExceptionHandler(value = TaskNotRunnableException.class)
    protected ResponseEntity<Object> handleTaskNotRunnableException(RuntimeException ex) {
        return handleConflict(HttpStatus.CONFLICT, ex);
    }

    private ResponseEntity<Object> handleConflict(HttpStatus status, RuntimeException ex) {
        ErrorResponse res = new ErrorResponse(ex.getMessage());
        return new ResponseEntity<>(res, status);
    }


    private class ErrorResponse {
        private String errorMessage;

        ErrorResponse(String errorMessage) {
            this.errorMessage = errorMessage;
        }

        public String getErrorMessage() {
            return errorMessage;
        }
    }
}
