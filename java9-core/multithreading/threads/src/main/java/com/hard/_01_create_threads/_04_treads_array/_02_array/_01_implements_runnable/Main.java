package com.hard._01_create_threads._04_treads_array._02_array._01_implements_runnable;

public class Main {
    public static void main(String[] args) {
        final int n = 2;

        Runnable[] runnables = new EntityThread[n];
        Thread[] threads = new Thread[n];

        for (int i = 0; i < threads.length; i++) {
            runnables[i] = new EntityThread();
            threads[i] = new Thread(runnables[i]);

            threads[i].start();
        }

        System.out.println(Thread.currentThread().getName() + ": Hello World");
    }
}

class EntityThread implements Runnable {
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + ": Hello World");
    }
}
