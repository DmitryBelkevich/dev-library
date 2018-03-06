package com.hard._01_create_threads._04_treads_array._01_collection._01_implements_runnable;

import java.util.ArrayList;
import java.util.Collection;

public class Main {
    public static void main(String[] args) {
        Collection<Runnable> runnables = new ArrayList<>();
        Collection<Thread> threads = new ArrayList<>();

        final int n = 2;
        for (int i = 0; i < n; i++) {
            Runnable runnable = new EntityThread();
            runnables.add(runnable);

            Thread thread = new Thread(runnable);
            threads.add(thread);
        }

        for (Thread thread : threads)
            thread.start();
    }
}

class EntityThread implements Runnable {
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + ": Hello World");
    }
}
