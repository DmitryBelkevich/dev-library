package com.hard._01_lazy_initialization;

public class Main {
    public static void main(String[] args) {
        Service service = new Service();
        service.operation();
    }
}

/**
 * Lazy initialization
 */

class Entity {
    public void operation() {
        System.out.println("operation");
    }
}

class Service {
    private Entity entity;

    public void operation() {
        if (entity == null)
            entity = new Entity();

        entity.operation();
    }
}
