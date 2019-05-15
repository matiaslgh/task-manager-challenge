package com.app.controllers.errors;

import com.app.exceptions.BadRequestException;
import com.app.exceptions.NotFoundException;
import com.app.exceptions.TooManyTasksRunningException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.TOO_MANY_REQUESTS;

public class RestResponseEntityExceptionHandlerTest {

    private static final String ERROR_MESSAGE = "ERROR_MESSAGE";

    private RestResponseEntityExceptionHandler handler = new RestResponseEntityExceptionHandler();

    @Test
    public void testHandleTooManyTasksRunningException() {
        TooManyTasksRunningException ex = new TooManyTasksRunningException(ERROR_MESSAGE);
        ResponseEntity<Object> res = handler.handleTooManyTasksRunningException(ex);

        assertEquals(TOO_MANY_REQUESTS.value(), res.getStatusCode().value());
    }

    @Test
    public void testHandleBadRequestException() {
        BadRequestException ex = new BadRequestException(ERROR_MESSAGE);
        ResponseEntity<Object> res = handler.handleBadRequestException(ex);

        assertEquals(BAD_REQUEST.value(), res.getStatusCode().value());
    }

    @Test
    public void testHandleNotFoundException() {
        NotFoundException ex = new NotFoundException(ERROR_MESSAGE);
        ResponseEntity<Object> res = handler.handleNotFoundException(ex);

        assertEquals(NOT_FOUND.value(), res.getStatusCode().value());
    }
}