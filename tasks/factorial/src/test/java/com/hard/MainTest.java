package com.hard;

import org.junit.Assert;
import org.junit.Test;

public class MainTest {
    @Test
    public void test1() {
        Assert.assertEquals(1, Main.factorial(1));
    }

    @Test
    public void test2() {
        Assert.assertEquals(1 * 2, Main.factorial(2));
    }

    @Test
    public void test3() {
        Assert.assertEquals(1 * 2 * 3, Main.factorial(3));
    }

    @Test
    public void test4() {
        Assert.assertEquals(1 * 2 * 3 * 4, Main.factorial(4));
    }

    @Test
    public void test5() {
        Assert.assertEquals(1 * 2 * 3 * 4 * 5, Main.factorial(5));
    }
}
