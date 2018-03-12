package com.hard._06_wait_notify;

public class Main {
    public static void main(String[] args) {
        Thread thread = new EntityThread();

        thread.start();

        synchronized (thread) {
            try {
                thread.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println(thread);

        System.out.println(Thread.currentThread().getName() + ": Hello World");
    }
}

class EntityThread extends Thread {
    @Override
    public void run() {
        synchronized (this) {
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName() + ": Hello World" + ": " + i);

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            this.notify();
        }
    }
}
