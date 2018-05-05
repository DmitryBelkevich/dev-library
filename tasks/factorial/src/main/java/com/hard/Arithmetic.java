package com.hard;

public class Arithmetic {
    static int factorial(int i) {
        if (i <= 1)
            return 1;

        int result = factorial(i - 1) * i;

        return result;
    }
}
