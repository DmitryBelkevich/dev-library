package com.hard;

public class Main {
    public static void main(String[] args) {
        Listener listener = new Listener() {
            @Override
            public void execute() {
                print();
            }
        };

        listener.execute();
    }
}

interface Listener {
    default void print() {
        System.out.println("Hello World");
    }

    void execute();
}
