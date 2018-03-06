package com.hard._01_create_threads._01_simple._01_implements_runnable;

public class Main {
    public static void main(String[] args) {
        Runnable runnable1 = new EntityThread();
        Runnable runnable2 = new EntityThread();

        Thread thread1 = new Thread(runnable1);
        Thread thread2 = new Thread(runnable2);

        thread1.start();
        thread2.start();
    }
}

class EntityThread implements Runnable {
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + ": Hello World");
    }
}
