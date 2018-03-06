package com.hard._01_create_threads._02_autorunnable._02_extends_thread;

public class Main {
    public static void main(String[] args) {
        new Entity();
        new Entity();
    }
}

class Entity extends Thread {
    public Entity() {
        this.start();
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + ": Hello World");
    }
}
