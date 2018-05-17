package com.hard._5_double_checked_locking;

import org.junit.Assert;
import org.junit.Test;

public class SingletonTest {
    /**
     * проверка на Singleton
     */

    @Test
    public void should_be_the_same_object_by_creating() {
        Singleton singleton1 = Singleton.getInstance();
        Singleton singleton2 = Singleton.getInstance();

        Assert.assertSame(singleton1, singleton2);
    }

    /**
     * проверка на многопоточность
     */

    @Test
    public void should_work_in_multithreading() {
        for (int i = 0; i < 1_000; i++) {
            new Thread(() -> {
                System.out.println(Singleton.getInstance());
            }).start();
        }
    }
}
