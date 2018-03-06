package com.hard._02_join._01_implements_runnable;

import java.util.ArrayList;
import java.util.Collection;

public class Main {
    public static void main(String[] args) {
        Collection<Thread> threads = new ArrayList<>();

        final int n = 2;
        for (int i = 0; i < n; i++) {
            Runnable runnable = new EntityThread();
            Thread thread = new Thread(runnable);

            threads.add(thread);
        }

        for (Thread thread : threads)
            thread.start();

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
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
