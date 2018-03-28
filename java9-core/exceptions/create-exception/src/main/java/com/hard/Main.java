package com.hard;

public class Main {
    public static void main(String[] args) {
        Arithmetic arithmetic = new Arithmetic();

        double result = 0;

        try {
            result = arithmetic.div(10, 0);
        } catch (DivisionByZeroException e) {
            e.printStackTrace();
        }

        System.out.println(result);
    }
}

/**
 * Exception
 */

class DivisionByZeroException extends Exception {
    public DivisionByZeroException() {
        super();
    }

    public DivisionByZeroException(String message) {
        super(message);
    }
}

/**
 * class
 */

class Arithmetic {
    public double div(double num1, double num2) throws DivisionByZeroException {
        if (num2 == 0)
            throw new DivisionByZeroException("second parameter was specified as zero. One can't divide by zero");

        return num1 / num2;
    }
}
