package com.app.utils;

import java.util.stream.IntStream;

public class NumberUtils {

    public static boolean isPrime(final int number) {
        return IntStream.rangeClosed(2, number/2).noneMatch(i -> number%i == 0);
    }
}
