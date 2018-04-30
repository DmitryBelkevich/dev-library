package com.hard._02;

import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        long start = System.nanoTime();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long finish = System.nanoTime();

        long millis = TimeUnit.NANOSECONDS.toMillis(finish - start);
        double seconds = millis * 0.001;

        System.out.println("consumed time: " + millis + " ms (" + seconds + " s)");
    }
}
