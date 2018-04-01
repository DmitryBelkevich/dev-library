package com.hard;

public class Main {
    public static void main(String[] args) {
        Facade facade = new Facade();
        facade.operation();
    }
}

/**
 * Subsystem
 */

class Entity1 {
    public void operation1() {
        System.out.println("operation1");
    }
}

class Entity2 {
    public void operation2() {
        System.out.println("operation2");
    }
}

/**
 * Facade
 */

class Facade {
    private Entity1 entity1 = new Entity1();
    private Entity2 entity2 = new Entity2();

    public void operation() {
        entity1.operation1();
        entity2.operation2();
    }
}
