package com.hard;

public class Main {
    public static void main(String[] args) {
        Listener listener = new Listener() {
            @Override
            public void execute() {
                System.out.println("Hello World");
            }
        };

        listener.execute();
    }
}

/**
 * функциональный интерфейс - интерфейс с одним объявленным методом
 */

interface Listener {
    void execute();
}
