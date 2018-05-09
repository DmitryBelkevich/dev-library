package com.hard._01_game_loop;

import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        long fps = 60;
        double targetTimeSeconds = 1d / fps;
        long targetTime = Math.round(targetTimeSeconds * 1000);

        int i = 0;
        while (true) {
            long startTime = System.nanoTime();
            process();
            long finishTime = System.nanoTime();
            long elapsedTime = TimeUnit.NANOSECONDS.toMillis(finishTime - startTime);

            long waitingTime = targetTime - elapsedTime;
            if (waitingTime < 0)
                waitingTime = 0;

            sleep(waitingTime);

            if (i >= 10)
                break;

            i++;
        }
    }

    public static void process() {
        try {
            long mills = (long) (Math.random() * 10);
            Thread.sleep(mills);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void sleep(long mills) {
        try {
            Thread.sleep(mills);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
