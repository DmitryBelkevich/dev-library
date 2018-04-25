package com.hard;

public class Main {
    public static void main(String[] args) {
        Listener listener = () -> {
            System.out.println("Hello World");
        };

        listener.execute();
    }
}

interface Listener {
    void execute();
}
