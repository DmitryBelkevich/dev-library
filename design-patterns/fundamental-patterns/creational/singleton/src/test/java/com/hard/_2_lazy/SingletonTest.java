package com.hard._2_lazy;

import org.junit.Assert;
import org.junit.Test;

public class SingletonTest {
    @Test
    public void shouldBeTheSameObjectByCreating() {
        Singleton singleton1 = Singleton.getInstance();
        Singleton singleton2 = Singleton.getInstance();

        Assert.assertEquals(singleton1, singleton2);
    }
}

class Singleton {
    private static Singleton instance;

    private Singleton() {

    }

    public static Singleton getInstance() {
        if (instance == null)
            instance = new Singleton();

        return instance;
    }
}
