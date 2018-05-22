package com.hard;

import org.junit.Assert;
import org.junit.Test;

public class InfixTest {
    public static class evaluate {
        @Test
        public void should_evaluate_expression_1() {
            Infix infix = new Infix();

            String expression = "(2.0 + 2.0) * 2.0";
            double result = infix.evaluate(expression);
            Assert.assertEquals(8, result, 0);
        }

        @Test
        public void should_evaluate_expression_2() {
            Infix infix = new Infix();

            String expression = "2.0 + 2.0 * 2.0";
            double result = infix.evaluate(expression);
            Assert.assertEquals(6, result, 0);
        }

        @Test
        public void should_evaluate_expression_3() {
            Infix infix = new Infix();

            String expression = "2.0 ^ 3.0 ^ 2.0";
            double result = infix.evaluate(expression);
            Assert.assertEquals(512, result, 0);
        }
    }

    public static class toPostfix {
        @Test
        public void should_convert_infix_to_postfix_1() {
            Infix infix = new Infix();

            String expression = "(2.0 + 2.0) * 2.0";
            String postfix = infix.toPostfix(expression);

            Assert.assertEquals("2.0 2.0 + 2.0 *", postfix);
        }

        @Test
        public void should_convert_infix_to_postfix_2() {
            Infix infix = new Infix();

            String expression = "2.0 + 2.0 * 2.0";
            String postfix = infix.toPostfix(expression);

            Assert.assertEquals("2.0 2.0 2.0 * +", postfix);
        }

        @Test
        public void should_convert_infix_to_postfix_3() {
            Infix infix = new Infix();

            String expression = "2.0 ^ 3.0 ^ 2.0";
            String postfix = infix.toPostfix(expression);

            Assert.assertEquals("2.0 3.0 2.0 ^ ^", postfix);
        }
    }

    public static class toPrefix {
        @Test
        public void should_convert_infix_to_prefix_1() {
            Infix infix = new Infix();

            String expression = "(2.0 + 2.0) * 2.0";
            String prefix = infix.toPrefix(expression);

            Assert.assertEquals("* + 2.0 2.0 2.0", prefix);
        }

        @Test
        public void should_convert_infix_to_prefix_2() {
            Infix infix = new Infix();

            String expression = "2.0 + 2.0 * 2.0";
            String prefix = infix.toPrefix(expression);

            Assert.assertEquals("+ * 2.0 2.0 2.0", prefix);
        }

        @Test
        public void should_convert_infix_to_prefix_3() {
            Infix infix = new Infix();

            String expression = "2.0 ^ 3.0 ^ 2.0";
            String prefix = infix.toPrefix(expression);

            Assert.assertEquals("^ 2.0 ^ 3.0 2.0", prefix);
        }
    }
}
