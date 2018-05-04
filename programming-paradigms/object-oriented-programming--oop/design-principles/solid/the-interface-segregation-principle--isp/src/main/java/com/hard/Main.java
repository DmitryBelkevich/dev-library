package com.hard;

public class Main {
    public static void main(String[] args) {
        Worker worker = new Cook();
        worker.work();
        worker.sleep();
    }
}

/**
 * Bad example
 */

interface Worker {
    void work();
    void sleep();
}

class Cook implements Worker {
    @Override
    public void work() {
        System.out.println("Cook is working");
    }

    @Override
    public void sleep() {
        System.out.println("Cook is sleeping");
    }
}

class Waiter implements Worker {
    @Override
    public void work() {
        System.out.println("Waiter is working");
    }

    @Override
    public void sleep() {

    }
}

/**
 * Good example
 */
