package com.hard;

import org.junit.Assert;
import org.junit.Test;

public class InfixTest {
    public static class infix_to_postfix {
        @Test
        public void should_convert_infix_to_postfix_1() {
            String infix = "(2.0+2.0)*2.0";
            String postfix = Infix.toPostfix(infix);

            Assert.assertSame("2.0 2.0 + 2.0 *", postfix);
        }

        @Test
        public void should_convert_infix_to_postfix_2() {
            String infix = "2+2*2";
            String postfix = Infix.toPostfix(infix);

            Assert.assertSame("2.0 2.0 2.0 * +", postfix);
        }

        @Test
        public void should_convert_infix_to_postfix_3() {
            String infix = "2^3^2";
            String postfix = Infix.toPostfix(infix);

            Assert.assertSame("2 3 2 ^ ^", postfix);
        }
    }

    public static class infix_to_prefix {
        @Test
        public void should_convert_infix_to_prefix_1() {
            String infix = "(2.0+2.0)*2.0";
            String prefix = Infix.toPrefix(infix);

            Assert.assertSame("* + 2.0 2.0 2.0", prefix);
        }

        @Test
        public void should_convert_infix_to_prefix_2() {
            String infix = "2.0+2.0*2.0";
            String prefix = Infix.toPrefix(infix);

            Assert.assertSame("+ * 2.0 2.0 2.0", prefix);
        }

        @Test
        public void should_convert_infix_to_prefix_3() {
            String infix = "2.0^3.0^2.0";
            String prefix = Infix.toPrefix(infix);

            Assert.assertSame("^ 2.0 ^ 3.0 2.0", prefix);
        }
    }
}
