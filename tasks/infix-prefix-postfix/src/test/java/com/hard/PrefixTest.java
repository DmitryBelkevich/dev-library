package com.hard;

import org.junit.Assert;
import org.junit.Test;

public class PrefixTest {
    @Test
    public void should_evaluate_expression_1() {
        Prefix prefix = new Prefix();

        String expression = "* + 2.0 2.0 2.0";  // infix: (2+2)*2
        double result = prefix.evaluate(expression);
        Assert.assertEquals(8, result, 0);
    }

    @Test
    public void should_evaluate_expression_2() {
        Prefix prefix = new Prefix();

        String expression = "* 2.0 2.0 + 2.0";  // infix: 2+2*2
        double result = prefix.evaluate(expression);
        Assert.assertEquals(6, result, 0);
    }

    @Test
    public void should_evaluate_expression_3() {
        Prefix prefix = new Prefix();

        String expression = "^ ^ 2.0 2.0 2.0";  // infix: 2^2^2
        double result = prefix.evaluate(expression);
        Assert.assertEquals(6, result, 0);
    }

    @Test//del
    public void should_evaluate_expression_4() {
        Prefix prefix = new Prefix();

        String expression = "- + * 2 3 + 5 4 9";  // infix: 2*3+5*4-9
        double result = prefix.evaluate(expression);
        Assert.assertEquals(17, result, 0);
    }
}
