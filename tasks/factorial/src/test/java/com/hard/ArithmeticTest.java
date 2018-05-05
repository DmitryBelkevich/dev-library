package com.hard;

import org.junit.Assert;
import org.junit.Test;

public class ArithmeticTest {
    @Test
    public void should_return_result_from_factorial_1() {
        Assert.assertEquals(1, Arithmetic.factorial(1));
    }

    @Test
    public void should_return_result_from_factorial_2() {
        Assert.assertEquals(1 * 2, Arithmetic.factorial(2));
    }

    @Test
    public void should_return_result_from_factorial_3() {
        Assert.assertEquals(1 * 2 * 3, Arithmetic.factorial(3));
    }

    @Test
    public void should_return_result_from_factorial_4() {
        Assert.assertEquals(1 * 2 * 3 * 4, Arithmetic.factorial(4));
    }

    @Test
    public void should_return_result_from_factorial_5() {
        Assert.assertEquals(1 * 2 * 3 * 4 * 5, Arithmetic.factorial(5));
    }
}
