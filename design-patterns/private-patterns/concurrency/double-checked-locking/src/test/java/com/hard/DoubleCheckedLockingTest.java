package com.hard;

import org.junit.Assert;
import org.junit.Test;

public class DoubleCheckedLockingTest {
    /**
     * проверка на Singleton
     */

    @Test
    public void should_be_the_same_object_by_creating() {
        DoubleCheckedLocking doubleCheckedLocking = new DoubleCheckedLocking();

        Assert.assertSame(doubleCheckedLocking.getEntity(), doubleCheckedLocking.getEntity());
    }

    /**
     * Проверка на lazy initialization
     */

    @Test
    public void should_have_eager_initialization() {
//        String str = DoubleCheckedLocking.str;
    }

    /**
     * проверка на многопоточность
     */

    @Test
    public void should_work_in_multithreading() {

    }
}
