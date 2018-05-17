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
        for (int i = 0; i < 1_000; i++) {
            new Thread(() -> {
                System.out.println(Singleton.getInstance());
            }).start();
        }
    }
}
