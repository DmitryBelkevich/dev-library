package com.hard._01_create_threads._03_anonymous_class._02_in_method._01_runnable;

public class Main {
    public static void main(String[] args) {
        new EntityThread().f();
        new EntityThread().f();

        System.out.println(Thread.currentThread().getName() + ": Hello World");
    }
}

class EntityThread {
    public void f() {
        new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        System.out.println(Thread.currentThread().getName() + ": Hello World");
                    }
                }
        ).start();
    }
}
