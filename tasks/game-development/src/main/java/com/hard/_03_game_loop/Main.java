package com.hard._03_game_loop;

import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        long fps = 60;
        double targetTimeSeconds = 1d / fps;
        long targetTimeMills = Math.round(targetTimeSeconds * 1000);

        int i = 0;
        while (true) {
            long startTimeNano = System.nanoTime();
            process();
            long finishTimeNano = System.nanoTime();
            long elapsedTimeNano = finishTimeNano - startTimeNano;
            long elapsedTimeMills = TimeUnit.NANOSECONDS.toMillis(elapsedTimeNano);

            long waitingTimeMills = targetTimeMills - elapsedTimeMills;
            if (waitingTimeMills < 0)
                waitingTimeMills = 0;

            sleep(waitingTimeMills);

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
