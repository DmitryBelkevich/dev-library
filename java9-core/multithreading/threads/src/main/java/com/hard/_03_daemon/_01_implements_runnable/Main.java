package com.hard._03_daemon._01_implements_runnable;

public class Main {
    public static void main(String[] args) {
        Runnable runnable1 = new EntityThread();
        Runnable runnable2 = new EntityThread();

        Thread thread1 = new Thread(runnable1);
        Thread thread2 = new Thread(runnable2);

        thread1.setDaemon(true);
        thread2.setDaemon(true);

        thread1.start();
        thread2.start();

        System.out.println(Thread.currentThread().getName() + ": Hello World");
    }
}

class EntityThread implements Runnable {
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + ": Hello World ");

        try {
            Thread.sleep(10 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
