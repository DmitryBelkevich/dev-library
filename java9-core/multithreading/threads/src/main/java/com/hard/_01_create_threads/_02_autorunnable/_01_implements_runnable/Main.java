package com.hard._01_create_threads._02_autorunnable._01_implements_runnable;

public class Main {
    public static void main(String[] args) {
        new EntityThread();
        new EntityThread();
    }
}

class EntityThread implements Runnable {
    private Thread thread;

    public EntityThread() {
        this.thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + ": Hello World");
    }
}
