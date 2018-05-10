package com.hard._01_lazy_initialization;

public class Main {
    public static void main(String[] args) {
        Service service = new Service();
        Entity entity = service.getEntity();
        entity.operation();
    }
}

/**
 * Lazy initialization
 */

class Entity {
    public Entity() {
        System.out.println("creating of object");
    }

    public void operation() {
        System.out.println("operation");
    }
}

class Service {
    private Entity entity;

    public Entity getEntity() {
        if (entity == null)
            entity = new Entity();

        return entity;
    }
}
