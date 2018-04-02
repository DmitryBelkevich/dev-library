package com.hard._2_advanced;

public class Main {
    public static void main(String[] args) {
        Delegator delegator = new Delegator();

        delegator.setDelegate(new Delegate1());
        delegator.operation();

        delegator.setDelegate(new Delegate2());
        delegator.operation();
    }
}

/**
 * Delegate
 */

interface Delegate {
    void operation();
}

/**
 * Concrete Delegate
 */

class Delegate1 implements Delegate {
    @Override
    public void operation() {
        System.out.println("Delegate1");
    }
}

class Delegate2 implements Delegate {
    @Override
    public void operation() {
        System.out.println("Delegate2");
    }
}

/**
 * Delegator
 */

class Delegator {
    private Delegate delegate;

    public void setDelegate(Delegate delegate) {    // mutator
        this.delegate = delegate;
    }

    public void operation() {
        delegate.operation();
    }
}
