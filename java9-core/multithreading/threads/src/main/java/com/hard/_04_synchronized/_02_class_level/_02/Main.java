package com.hard._04_synchronized._02_class_level._02;

import java.util.ArrayList;
import java.util.Collection;

public class Main {
    public static void main(String[] args) {
        Collection<Thread> threads = new ArrayList<>();

        final int n = 10;
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

class Entity {
    public static void execute() {
        synchronized (Entity.class) {
            System.out.println(Thread.currentThread().getName() + ": Hello World");

            try {
                Thread.sleep(1 * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class EntityThread implements Runnable {
    @Override
    public void run() {
        Entity.execute();
    }
}
