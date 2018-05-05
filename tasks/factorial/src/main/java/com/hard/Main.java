package com.hard;

public class Main {
    static int factorial(int i) {
        if (i <= 1)
            return 1;

        int result = factorial(i - 1) * i;

        return result;
    }
}
