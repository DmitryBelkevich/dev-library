package com.hard;

import org.junit.Assert;
import org.junit.Test;

public class PostfixTest {
    @Test
    public void should_evaluate_expression_1() {
        Postfix postfix = new Postfix();

        String expression = "2.0 2.0 + 2.0 *";  // infix: (2+2)*2
        double result = postfix.evaluate(expression);
        Assert.assertEquals(8, result, 0);
    }

    @Test
    public void should_evaluate_expression_2() {
        Postfix postfix = new Postfix();

        String expression = "2.0 2.0 2.0 * +";  // infix: 2+2*2
        double result = postfix.evaluate(expression);
        Assert.assertEquals(6, result, 0);
    }
}
