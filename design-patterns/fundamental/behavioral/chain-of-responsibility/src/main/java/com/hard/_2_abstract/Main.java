package com.hard._2_abstract;

public class Main {
    public static void main(String[] args) {
        Handler handler1 = new Handler1(Level.LOW);
        Handler handler2 = new Handler2(Level.MIDDLE);
        Handler handler3 = new Handler3(Level.HIGH);

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

abstract class Handler {
    protected int priority;
    protected Handler nextHandler;

    public Handler(int priority) {
        this.priority = priority;
    }

    public void setNextHandler(Handler nextHandler) {
        this.nextHandler = nextHandler;
    }

    public abstract void handleRequest(String message, int level);
}

/**
 * Concrete Handler
 */

class Handler1 extends Handler {
    public Handler1(int priority) {
        super(priority);
    }

    @Override
    public void handleRequest(String message, int level) {
        if (level >= priority)
            System.out.println("Handler1: " + message);

        if (nextHandler != null)
            nextHandler.handleRequest(message, level);
    }
}

class Handler2 extends Handler {
    public Handler2(int priority) {
        super(priority);
    }

    @Override
    public void handleRequest(String message, int level) {
        if (level >= priority)
            System.out.println("Handler2: " + message);

        if (nextHandler != null)
            nextHandler.handleRequest(message, level);
    }
}

class Handler3 extends Handler {
    public Handler3(int priority) {
        super(priority);
    }

    @Override
    public void handleRequest(String message, int level) {
        if (level >= priority)
            System.out.println("Handler2: " + message);

        if (nextHandler != null)
            nextHandler.handleRequest(message, level);
    }
}
