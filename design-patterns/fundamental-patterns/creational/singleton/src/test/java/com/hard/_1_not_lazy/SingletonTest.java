package com.hard._1_not_lazy;

import org.junit.Assert;
import org.junit.Test;

public class SingletonTest {
    @Test
    public void shouldBeTheSameObjectByCreating() {
        Singleton singleton1 = Singleton.getInstance();
        Singleton singleton2 = Singleton.getInstance();

        Assert.assertEquals(singleton1, singleton2);
    }

    @Test
    public void shouldWorkInMultithreading() {
        Singleton singleton = Singleton.getInstance();

        final int n = 1 * 1000;
        for (int i = 0; i < n; i++) {
            new Thread(() -> {
                Assert.assertEquals(singleton, Singleton.getInstance());
            }).start();
        }
    }
}

class Singleton {
    private static Singleton instance = new Singleton();

    private Singleton() {

    }

    public static Singleton getInstance() {
        return instance;
    }
}
