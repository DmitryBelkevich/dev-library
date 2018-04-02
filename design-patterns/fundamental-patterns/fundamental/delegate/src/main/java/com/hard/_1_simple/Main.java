package com.hard._1_simple;

public class Main {
    public static void main(String[] args) {
        Delegate delegate = new Delegate();
        delegate.operation();

        Delegator delegator = new Delegator();
        delegator.operation();
    }
}

class Delegate {
    public void operation() {
        System.out.println("Hello World");
    }
}

class Delegator {
    private Delegate delegate = new Delegate();

    public void operation() {
        delegate.operation();
    }
}
