package com.app.tasks.models;

import org.junit.jupiter.api.Test;

import static com.app.tasks.models.TaskStatus.*;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TaskStatusTest {

    @Test
    void testWaitingForRunningIsRunnable() {
        assertTrue(WAITING_FOR_RUNNING.isRunnable());
    }

    @Test
    void testWaitingForRunningHasItsResultHidden() {
        assertFalse(WAITING_FOR_RUNNING.isResultVisible());
    }

    @Test
    void testRunningIsNotRunnable() {
        assertFalse(RUNNING.isRunnable());
    }

    @Test
    void testRunningHasItsResultHidden() {
        assertFalse(RUNNING.isResultVisible());
    }

    @Test
    void testCompletedIsNotRunnable() {
        assertFalse(COMPLETED.isRunnable());
    }

    @Test
    void testCompletedHasItsResultVisible() {
        assertTrue(COMPLETED.isResultVisible());
    }

    @Test
    void testFailedIsNotRunnable() {
        assertFalse(FAILED.isRunnable());
    }

    @Test
    void testFailedHasItsResultHidden() {
        assertFalse(FAILED.isResultVisible());
    }
}