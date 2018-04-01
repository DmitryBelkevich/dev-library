package com.hard;

public class Main {
    public static void main(String[] args) {
        Prototype prototype1 = new Prototype();

        PrototypeFactory prototypeFactory = new PrototypeFactory(prototype1);
        Prototype prototype2 = prototypeFactory.makeCopy();

        prototypeFactory.setPrototype(prototype2);
        Prototype prototype3 = prototypeFactory.makeCopy();

        System.out.println(prototype1);
        System.out.println(prototype2);
        System.out.println(prototype3);
    }
}

/**
 * Prototype
 */

interface Copyable {
    Object copy();
}

/**
 * Concrete Prototype
 */

class Prototype implements Copyable {
    @Override
    public Object copy() {
        return new Prototype();
    }
}

/**
 * Client
 */

class PrototypeFactory {
    private Prototype prototype;

    public PrototypeFactory(Prototype prototype) {
        this.prototype = prototype;
    }

    public void setPrototype(Prototype prototype) {
        this.prototype = prototype;
    }

    public Prototype makeCopy() {
        return (Prototype) prototype.copy();
    }
}
