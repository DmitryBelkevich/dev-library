package com.hard._04_synchronized._02_class_level._03;

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

class Entity2 {

}

class Entity {
    private final static Entity2 entity2 = new Entity2();

    public static void execute() {
        synchronized (entity2) {
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
