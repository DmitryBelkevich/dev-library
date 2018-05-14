package com.hard;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class ListTest {
    private static final int n = 10 * 1000 * 1000;
    private static List<String> list;

    public static class ArrayListTest {
        @Before
        public void init() {
            list = new ArrayList<>();

            for (int i = 0; i < n; i++)
                list.add(String.valueOf(i));
        }

        @After
        public void destroy() {
            list = null;
        }

        /**
         * get
         */

        @Test
        public void should_fast_get_by_id_from_begin() {
            long start = System.nanoTime();

            for (int i = 0; i < n; i++)
                list.get(0);

            long elapsed = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - start);

            int min = 5;
            int max = 7;
            double average = (min + max) / 2;
            double delta = (max - min) / 2;
            Assert.assertEquals(average, elapsed, delta);
        }

        @Test
        public void should_fast_get_by_id_from_end() {
            long start = System.nanoTime();

            for (int i = 0; i < n; i++)
                list.get(n - 1);

            long elapsed = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - start);

            int min = 5;
            int max = 18;
            double average = (min + max) / 2;
            double delta = (max - min) / 2;
            Assert.assertEquals(average, elapsed, delta);
        }

        /**
         * add
         */

        @Test
        public void should_too_long_add_to_begin() {
            long start = System.nanoTime();

            for (int i = 0; i < 100; i++)
                list.add(0, String.valueOf(n));

            long elapsed = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - start);

            int min = 2300;
            int max = 3400;
            double average = (min + max) / 2;
            double delta = (max - min) / 2;
            Assert.assertEquals(average, elapsed, delta);
        }

        @Test
        public void should_long_add_to_end() {
            long start = System.nanoTime();

            for (int i = 0; i < n; i++)
                list.add(String.valueOf(n));

            long elapsed = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - start);

            int min = 600;
            int max = 1700;
            double average = (min + max) / 2;
            double delta = (max - min) / 2;
            Assert.assertEquals(average, elapsed, delta);
        }

        /**
         * remove
         */

        @Test(timeout = 10000)
        public void should_too_long_remove_from_begin() {
            long start = System.nanoTime();

            for (int i = 0; i < n; i++)
                list.remove(0);

            long elapsed = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - start);

            int min = 0;
            int max = 0;
            double average = (min + max) / 2;
            double delta = (max - min) / 2;
            Assert.assertEquals(average, elapsed, delta);
        }

        @Test
        public void should_fast_remove_from_end() {
            long start = System.nanoTime();

            for (int i = 0; i < n; i++) {
                list.remove(n - 1);
                i--;
            }

            long elapsed = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - start);

            int min = 0;
            int max = 0;
            double average = (min + max) / 2;
            double delta = (max - min) / 2;
            Assert.assertEquals(average, elapsed, delta);
        }
    }

    public static class LinkedListTest {
        @Before
        public void init() {
            list = new LinkedList<>();

            for (int i = 0; i < n; i++)
                list.add(String.valueOf(i));
        }

        @After
        public void destroy() {
            list = null;
        }

        /**
         * get
         */

        @Test
        public void should_fast_get_by_id_from_begin() {
            long start = System.nanoTime();

            for (int i = 0; i < n; i++)
                list.get(0);

            long elapsed = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - start);

            int min = 6;
            int max = 7;
            double average = (min + max) / 2;
            double delta = (max - min) / 2;
            Assert.assertEquals(average, elapsed, delta);
        }

        @Test
        public void should_fast_get_by_id_from_end() {
            long start = System.nanoTime();

            for (int i = 0; i < n; i++)
                list.get(n - 1);

            long elapsed = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - start);

            int min = 5;
            int max = 10;
            double average = (min + max) / 2;
            double delta = (max - min) / 2;
            Assert.assertEquals(average, elapsed, delta);
        }

        /**
         * add
         */

        @Test
        public void should_long_add_to_begin() {
            long start = System.nanoTime();

            for (int i = 0; i < n; i++)
                list.add(0, String.valueOf(n));

            long elapsed = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - start);

            int min = 1900;
            int max = 2800;
            double average = (min + max) / 2;
            double delta = (max - min) / 2;
            Assert.assertEquals(average, elapsed, delta);
        }

        @Test
        public void should_long_add_to_end() {
            long start = System.nanoTime();

            for (int i = 0; i < n; i++)
                list.add(String.valueOf(n));

            long elapsed = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - start);

            int min = 1800;
            int max = 4400;
            double average = (min + max) / 2;
            double delta = (max - min) / 2;
            Assert.assertEquals(average, elapsed, delta);
        }

        /**
         * remove
         */

        @Test
        public void should_fast_remove_from_begin() {
            long start = System.nanoTime();

            for (int i = 0; i < n; i++)
                list.remove(0);

            long elapsed = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - start);

            int min = 0;
            int max = 0;
            double average = (min + max) / 2;
            double delta = (max - min) / 2;
            Assert.assertEquals(average, elapsed, delta);
        }

        @Test
        public void should_fast_remove_from_end() {
            long start = System.nanoTime();

            for (int i = 0; i < n; i++)
                list.remove(n - 1);

            long elapsed = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - start);

            int min = 0;
            int max = 0;
            double average = (min + max) / 2;
            double delta = (max - min) / 2;
            Assert.assertEquals(average, elapsed, delta);
        }
    }
}
