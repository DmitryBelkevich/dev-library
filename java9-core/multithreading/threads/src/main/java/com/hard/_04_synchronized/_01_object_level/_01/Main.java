package com.hard._04_synchronized._01_object_level._01;

import java.util.ArrayList;
import java.util.Collection;

public class Main {
    public static void main(String[] args) {
        Entity entity = new Entity();

        Collection<Thread> threads = new ArrayList<>();

        final int n = 10;
        for (int i = 0; i < n; i++) {
            Runnable runnable = new EntityThread(entity);
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
    public synchronized void execute() {	// Блокировка на уровне объекта (1)
        System.out.println(Thread.currentThread().getName() + ": Hello World");

        try {
            Thread.sleep(1 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class EntityThread implements Runnable {
    private Entity entity;

    public EntityThread(Entity entity) {
        this.entity = entity;
    }

    @Override
    public void run() {
        entity.execute();
    }
}
