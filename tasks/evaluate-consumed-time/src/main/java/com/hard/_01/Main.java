package com.hard._01;

public class Main {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long finish = System.currentTimeMillis();

        long millis = finish - start;
        double seconds = millis * 0.001;

        System.out.println("consumed time: " + millis + " ms (" + seconds + " s)");
    }
}
