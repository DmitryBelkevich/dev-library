package com.hard._01_create_threads._03_anonymous_class._03_method_returns._01_runnable;

public class Main {
    public static void main(String[] args) {
        Runnable runnable1 = new EntityThread().getRunnable();
        Runnable runnable2 = new EntityThread().getRunnable();

        Thread thread1 = new Thread(runnable1);
        Thread thread2 = new Thread(runnable2);

        thread1.start();
        thread2.start();
    }
}

class EntityThread {
    public Runnable getRunnable() {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + ": Hello World");
            }
        };

        return runnable;
    }
}
