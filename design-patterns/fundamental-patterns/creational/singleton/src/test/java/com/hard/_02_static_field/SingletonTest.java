package com.hard._02_static_field;

import org.junit.Assert;
import org.junit.Test;

public class SingletonTest {
    /**
     * проверка на Singleton
     */

    @Test
    public void should_be_the_same_object_by_creating() {
        Singleton singleton1 = Singleton.instance;
        Singleton singleton2 = Singleton.instance;

        Assert.assertSame(singleton1, singleton2);
    }

    /**
     * Проверка на lazy initialization
     */

    @Test
    public void should_have_eager_initialization() {
//        String str = Singleton.str;
    }

    /**
     * проверка на многопоточность
     */

    @Test
    public void should_work_in_multithreading() {
        final int n = 1_000;
        Singleton[] singletons = new Singleton[n];

        Thread[] threads = new Thread[n];

        for (int i = 0; i < n; i++) {
            int j = i;

            threads[i] = new Thread(() -> {
                singletons[j] = Singleton.instance;
            });
        }

        for (Thread thread : threads)
            thread.start();

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        for (int i = 1; i < n; i++)
            Assert.assertSame(singletons[0], singletons[i]);
    }
}
