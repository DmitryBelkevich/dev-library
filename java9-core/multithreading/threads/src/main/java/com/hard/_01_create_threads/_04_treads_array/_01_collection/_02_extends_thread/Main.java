package com.hard._01_create_threads._04_treads_array._01_collection._02_extends_thread;

import java.util.ArrayList;
import java.util.Collection;

public class Main {
    public static void main(String[] args) {
        Collection<Thread> threads = new ArrayList<>();

        final int n = 2;
        for (int i = 0; i < n; i++) {
            Thread thread = new EntityThread();
            threads.add(thread);
        }

        for (Thread thread : threads)
            thread.start();
    }
}

class EntityThread extends Thread {
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + ": Hello World");
    }
}
