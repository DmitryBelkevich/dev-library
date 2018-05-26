package com.hard._01_testing_private_methods;

import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class CalculatorTest {
    @Test
    public void test() {
        Calculator calculator = new Calculator();

        Class<?> clazz = calculator.getClass();

        Method method = null;
        try {
            method = clazz.getDeclaredMethod("sum", double.class, double.class);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        method.setAccessible(true);

        double result = 0;
        try {
            result = (double) method.invoke(calculator, 2, 3);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        Assert.assertEquals(5, result, 0);
    }
}
