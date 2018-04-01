package com.hard;

public class Main {
    public static void main(String[] args) {
        Entity entity = getEntity("Entity1");
        entity.templateMethod();
    }

    public static Entity getEntity(String name) {
        switch (name) {
            case "Entity1":
                return new Entity1();
            case "Entity2":
                return new Entity2();
            default:
                throw new RuntimeException();
        }
    }
}

/**
 * Abstract Class
 */

abstract class Entity {
    public abstract void subOperation1();

    public abstract void subOperation2();

    public final void templateMethod() {
        System.out.println("Header");
        subOperation1();
        subOperation2();
        System.out.println("Footer");
    }
}

/**
 * Concrete Class
 */

class Entity1 extends Entity {
    @Override
    public void subOperation1() {
        System.out.println("Body 1");
    }

    @Override
    public void subOperation2() {
        System.out.println("Navifation 1");
    }
}

class Entity2 extends Entity {
    @Override
    public void subOperation1() {
        System.out.println("Body 2");
    }

    @Override
    public void subOperation2() {

    }
}
