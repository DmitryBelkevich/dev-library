package com.hard;

import org.junit.Assert;
import org.junit.Test;

public class PostfixTest {
    @Test
    public void test1() {
        Postfix postfix = new Postfix();

        String expression = "2.0 2.0 + 2.0 *";  // infix: (2+2)*2
        double result = postfix.evaluate(expression);
        Assert.assertEquals(8, result, 0);
    }

    @Test
    public void test2() {
        Postfix postfix = new Postfix();

        String expression = "2.0 2.0 2.0 * +";  // infix: 2+2*2
        double result = postfix.evaluate(expression);
        Assert.assertEquals(6, result, 0);
    }
}
