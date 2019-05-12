package com.app.utils;

import com.app.exceptions.FakeException;
import org.junit.jupiter.api.Test;

import static com.app.utils.SimulationUtils.simulateFailure;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SimulationUtilsTest {

    @Test
    public void testSimulateFailure() throws FakeException {
        assertThrows(FakeException.class, () -> simulateFailure(1) );
        simulateFailure(0);
    }
}