package com.hard._03_value_holder;

public class Main {
    public static void main(String[] args) {
        Service service = new Service();
        Entity entity = service.getEntity();
        entity.operation();
    }
}

/**
 * Value holder
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
    private static class ValueHolder {
        private static Entity getValue() {
            return new Entity();
        }
    }

    public Entity getEntity() {
        Entity entity = ValueHolder.getValue();

        return entity;
    }
}
