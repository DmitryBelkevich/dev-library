package com.hard._05_interruption.my._02;

public class Main {
    public static void main(String[] args) {
        Entity entity = new Entity();

        entity.start();

        try {
            Thread.sleep(2 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        entity.interrupt();

        System.out.println(Thread.currentThread().getName());
    }
}

class Entity extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println(Thread.currentThread().getName() + ": i = " + i);

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("interrupting of " + Thread.currentThread().getName());
                return;
            }
        }
    }
}
