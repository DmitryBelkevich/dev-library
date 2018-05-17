package com.hard._07_Initialization_on_Demand_Holder;

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
     * Проверка на lazy initialization
     */

    @Test
    public void should_have_lazy_initialization() {
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
                singletons[j] = Singleton.getInstance();
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
