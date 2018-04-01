package com.hard;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        FlyweightFactory flyweightFactory = new FlyweightFactory();

        Flyweight flyweight1 = flyweightFactory.getFlyweight("FlyweightA");
        Flyweight flyweight2 = flyweightFactory.getFlyweight("FlyweightA");

        System.out.println(flyweight1);
        System.out.println(flyweight2);
    }
}

/**
 * Flyweight
 */

interface Flyweight {
    void operation();
}

/**
 * Concrete Flyweight
 */

class FlyweightA implements Flyweight {
    @Override
    public void operation() {
        System.out.println("FlyweightA: ");
    }
}

class FlyweightB implements Flyweight {
    @Override
    public void operation() {
        System.out.println("FlyweightB: ");
    }
}

/**
 * Flyweight Factory
 */

class FlyweightFactory {
    private static final Map<String, Flyweight> flyweights = new HashMap<>();

    public Flyweight getFlyweight(String name) {
        Flyweight flyweight = flyweights.get(name);

        if (flyweight == null) {
            switch (name) {
                case "FlyweightA":
                    flyweight = new FlyweightA();
                    break;
                case "FlyweightB":
                    flyweight = new FlyweightB();
                    break;
            }

            flyweights.put(name, flyweight);
        }

        return flyweight;
    }
}
