package com.hard._03_daemon._02_extends_thread;

public class Main {
    public static void main(String[] args) {
        Thread thread1 = new EntityThread();
        Thread thread2 = new EntityThread();

        thread1.setDaemon(true);
        thread2.setDaemon(true);

        thread1.start();
        thread2.start();

        System.out.println(Thread.currentThread().getName() + ": Hello World");
    }
}

class EntityThread extends Thread {
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
