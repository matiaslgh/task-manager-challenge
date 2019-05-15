package com.app.utils;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static com.app.utils.NumberUtils.isPrime;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class NumberUtilsTest {

    @Test
    void testIsPrimeWithPrimeNumbers() {
        int[] primeNumbers = {2, 5, 11, 79, 131, 1019, 4177, 7919 };
        Arrays.stream(primeNumbers).forEach(num -> assertTrue(isPrime(num), "failed with " + num));
    }

    @Test
    void testIsPrimeWithNonPrimeNumbers() {
        int[] primeNumbers = {4, 10, 95, 133, 793, 1027, 4035, 7900 };
        Arrays.stream(primeNumbers).forEach(num -> assertFalse(isPrime(num), "failed with " + num));
    }

}