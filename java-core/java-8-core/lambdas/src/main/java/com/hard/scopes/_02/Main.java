package com.hard.scopes._02;

public class Main {
    public static void main(String[] args) {

    }
}

interface Listener {
    void execute();
}

class Lambda4 {
    private int b = 1;
    private static int c = 2;

    private Listener listener1 = () -> {
        b = 1;
    };

    private Listener listener2 = () -> {
        c = 2;
    };
}
