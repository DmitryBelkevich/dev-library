package com.hard._05_wait_notify._02;

public class Main {
    public static void main(String[] args) {
        Object sync = new Object();

        new Thread(() -> {
            synchronized (sync) {
                System.out.println(Thread.currentThread().getName() + ": is waiting");

                try {
                    sync.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println(Thread.currentThread().getName() + ": end of waiting");
            }
        }).start();

        new Thread(() -> {
            synchronized (sync) {
                System.out.println(Thread.currentThread().getName() + ": is waiting");

                try {
                    sync.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println(Thread.currentThread().getName() + ": end of waiting");
            }
        }).start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(() -> {
            synchronized (sync) {
                System.out.println(Thread.currentThread().getName() + ": notify");
                sync.notifyAll();
            }
        }).start();
    }
}
