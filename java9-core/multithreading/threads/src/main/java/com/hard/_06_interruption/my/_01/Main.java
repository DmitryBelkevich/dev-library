package com.hard._06_interruption.my._01;

public class Main {
    public static void main(String[] args) {
        Entity entity = new Entity();

        entity.start();

        try {
            Thread.sleep(2 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        entity.setFinish(true);

        System.out.println(Thread.currentThread().getName());
    }
}

class Entity extends Thread {
    private volatile boolean finish;

    public void setFinish(boolean finish) {
        this.finish = finish;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            if (!finish) {
                System.out.println(Thread.currentThread().getName() + ": i = " + i);

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("interrupting of " + Thread.currentThread().getName());
                return;
            }
        }
    }
}
