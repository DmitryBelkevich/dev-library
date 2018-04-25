package com.hard._01_create_threads._04_treads_array._02_array._02_extends_thread;

public class Main {
    public static void main(String[] args) {
        final int n = 2;

        Thread[] threads = new Thread[n];

        for (int i = 0; i < threads.length; i++) {
            threads[i] = new EntityThread();

            threads[i].start();
        }

        System.out.println(Thread.currentThread().getName() + ": Hello World");
    }
}

class EntityThread extends Thread {
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + ": Hello World");
    }
}
