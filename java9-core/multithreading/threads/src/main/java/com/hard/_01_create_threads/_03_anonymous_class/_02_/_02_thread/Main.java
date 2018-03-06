package com.hard._01_create_threads._03_anonymous_class._02_._02_thread;

public class Main {
    public static void main(String[] args) {
        new EntityThread().f();
        new EntityThread().f();
    }
}

class EntityThread {
    public void f() {
        new Thread() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + ": Hello World");
            }
        }.start();
    }
}
