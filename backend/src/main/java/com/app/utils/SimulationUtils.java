package com.app.utils;

import com.app.exceptions.FakeException;

import java.util.Random;

public class SimulationUtils {

    /**
     * Throws a FakeException to simulate a failure with the probability provided as failureRate
     *
     * @param failureRate float between 0 and 1
     * @throws FakeException
     */
    public static void simulateFailure(float failureRate) throws FakeException {
        Random random = new Random();
        if (failureRate > random.nextFloat()) {
            throw new FakeException("This is a simulated failure. failureRate = " + failureRate);
        }
    }

}
