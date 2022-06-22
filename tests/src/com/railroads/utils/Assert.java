package com.railroads.utils;


import java.util.ArrayList;

public class Assert {

    public static void assertEquals(String actualString, String expectedString) throws AssertionError {
        if (!actualString.equals(expectedString)) {
            throw new AssertionError(String.format("\n\tExpected: %s\n\tActual: %s%n", expectedString, actualString));
        }
    }

    public static void assertEquals(ArrayList<ArrayList<String>> actualArray, ArrayList<ArrayList<String>> expectedArray) throws Exception {
        if (!actualArray.equals(expectedArray)) {
            throw new AssertionError(String.format("\n\tExpected: %s\n\tActual: %s%n", expectedArray, actualArray));
        }
    }
}
