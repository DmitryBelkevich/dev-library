package com.hard;

import org.junit.Assert;
import org.junit.Test;

public class InfixTest {
    public static class infix_to_postfix {
        @Test
        public void should_convert_infix_to_postfix_1() {
            Infix infix = new Infix();

            String expression = "(2.0+2.0)*2.0";
            String postfix = infix.toPostfix(expression);

            Assert.assertSame("2.0 2.0 + 2.0 *", postfix);
        }

        @Test
        public void should_convert_infix_to_postfix_2() {
            Infix infix = new Infix();

            String expression = "2+2*2";
            String postfix = infix.toPostfix(expression);

            Assert.assertSame("2.0 2.0 2.0 * +", postfix);
        }

        @Test
        public void should_convert_infix_to_postfix_3() {
            Infix infix = new Infix();

            String expression = "2^3^2";
            String postfix = infix.toPostfix(expression);

            Assert.assertSame("2 3 2 ^ ^", postfix);
        }
    }

    public static class infix_to_prefix {
        @Test
        public void should_convert_infix_to_prefix_1() {
            Infix infix = new Infix();

            String expression = "(2.0+2.0)*2.0";
            String prefix = infix.toPrefix(expression);

            Assert.assertSame("* + 2.0 2.0 2.0", prefix);
        }

        @Test
        public void should_convert_infix_to_prefix_2() {
            Infix infix = new Infix();

            String expression = "2.0+2.0*2.0";
            String prefix = infix.toPrefix(expression);

            Assert.assertSame("+ * 2.0 2.0 2.0", prefix);
        }

        @Test
        public void should_convert_infix_to_prefix_3() {
            Infix infix = new Infix();

            String expression = "2.0^3.0^2.0";
            String prefix = infix.toPrefix(expression);

            Assert.assertSame("^ 2.0 ^ 3.0 2.0", prefix);
        }
    }
}
