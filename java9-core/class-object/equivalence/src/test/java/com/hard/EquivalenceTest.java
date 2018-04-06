package com.hard;

import org.junit.Assert;
import org.junit.Test;

public class EquivalenceTest {
    @Test
    public void reflexivityTest() {
        String str1 = new String("Hello World");
        String str2 = new String("Hello World");

        boolean result = str1.equals(str2);

        Assert.assertEquals(true, result);
    }

    @Test
    public void symmetryTest() {
        String str1 = new String("Hello World");
        String str2 = new String("Hello World");

        boolean result1 = str1.equals(str2);

        Assert.assertEquals(true, result1);

        boolean result2 = str2.equals(str1);

        Assert.assertEquals(true, result2);
    }

    @Test
    public void transitivityTest() {
        String str1 = new String("Hello World");
        String str2 = new String("Hello World");
        String str3 = new String("Hello World");

        boolean result1 = str1.equals(str2);

        Assert.assertEquals(true, result1);

        boolean result2 = str2.equals(str3);

        Assert.assertEquals(true, result2);

        boolean result3 = str1.equals(str3);

        Assert.assertEquals(true, result3);
    }
}
