package com.hard;

public class Main {
    public static void main(String[] args) {
        Arithmetic arithmetic = new Arithmetic();

        double result = 0;

        try {
            result = arithmetic.div(10, 0);
        } catch (DivideByZeroException e) {
            e.printStackTrace();
        }

        System.out.println(result);
    }
}

/**
 * Exception
 */

class DivideByZeroException extends Exception {
    public DivideByZeroException() {
        super();
    }

    public DivideByZeroException(String message) {
        super(message);
    }
}

/**
 * class
 */

class Arithmetic {
    public double div(double num1, double num2) throws DivideByZeroException {
        if (num2 == 0)
            throw new DivideByZeroException("second parameter was specified as zero. One can't divide by zero");

        return num1 / num2;
    }
}
