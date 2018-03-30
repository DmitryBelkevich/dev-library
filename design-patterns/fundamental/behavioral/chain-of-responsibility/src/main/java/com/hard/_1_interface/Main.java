package com.hard._1_interface;

public class Main {
    public static void main(String[] args) {
        Handler1 handler1 = new Handler1(Level.LOW);
        Handler2 handler2 = new Handler2(Level.MIDDLE);
        Handler3 handler3 = new Handler3(Level.HIGH);

        handler1.setNextHandler(handler2);
        handler2.setNextHandler(handler3);

        System.out.println("--- LOW: ---");
        handler1.handleRequest("low importance", Level.LOW);

        System.out.println("--- MIDDLE: ---");
        handler1.handleRequest("middle importance", Level.MIDDLE);

        System.out.println("--- HIGH: ---");
        handler1.handleRequest("high importance", Level.HIGH);
    }
}

class Level {
    public static final int LOW = 1;
    public static final int MIDDLE = 2;
    public static final int HIGH = 3;
}

/**
 * Handler
 */

interface Handler {
    void handleRequest(String message, int level);
}

/**
 * Concrete Handler
 */

class Handler1 implements Handler {
    private int priority;
    private Handler nextHandler;

    public Handler1(int priority) {
        this.priority = priority;
    }

    public void setNextHandler(Handler nextHandler) {
        this.nextHandler = nextHandler;
    }

    @Override
    public void handleRequest(String message, int level) {
        if (level >= priority)
            System.out.println("Handler1: " + message);

        if (nextHandler != null)
            nextHandler.handleRequest(message, level);
    }
}

class Handler2 implements Handler {
    private int priority;
    private Handler nextHandler;

    public Handler2(int priority) {
        this.priority = priority;
    }

    public void setNextHandler(Handler nextHandler) {
        this.nextHandler = nextHandler;
    }

    @Override
    public void handleRequest(String message, int level) {
        if (level >= priority)
            System.out.println("Handler2: " + message);

        if (nextHandler != null)
            nextHandler.handleRequest(message, level);
    }
}

class Handler3 implements Handler {
    private int priority;
    private Handler nextHandler;

    public Handler3(int priority) {
        this.priority = priority;
    }

    public void setNextHandler(Handler nextHandler) {
        this.nextHandler = nextHandler;
    }

    @Override
    public void handleRequest(String message, int level) {
        if (level >= priority)
            System.out.println("Handler3: " + message);

        if (nextHandler != null)
            nextHandler.handleRequest(message, level);
    }
}
