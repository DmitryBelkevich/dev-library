package com.hard._01_create_threads._03_anonymous_class._03_method_returns._02_thread;

public class Main {
    public static void main(String[] args) {
        Thread thread1 = new EntityThread().getThread();
        Thread thread2 = new EntityThread().getThread();

        thread1.start();
        thread2.start();

        System.out.println(Thread.currentThread().getName() + ": Hello World");
    }
}

class EntityThread {
    public Thread getThread() {
        Thread thread = new Thread() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + ": Hello World");
            }
        };

        return thread;
    }
}
