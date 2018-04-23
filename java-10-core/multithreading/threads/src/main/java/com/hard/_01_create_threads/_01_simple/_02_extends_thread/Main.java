package com.hard._01_create_threads._01_simple._02_extends_thread;

public class Main {
    public static void main(String[] args) {
        Thread thread1 = new EntityThread();
        Thread thread2 = new EntityThread();

        thread1.start();
        thread2.start();

        System.out.println(Thread.currentThread().getName() + ": Hello World");
    }
}

class EntityThread extends Thread {
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + ": Hello World");
    }
}
