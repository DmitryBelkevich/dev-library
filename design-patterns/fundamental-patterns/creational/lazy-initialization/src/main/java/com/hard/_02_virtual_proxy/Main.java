package com.hard._02_virtual_proxy;

public class Main {
    public static void main(String[] args) {
        IEntity entity = new ServiceProxy();
        entity.operation();
    }
}

/**
 * Virtual proxy
 */

interface IEntity {
    void operation();
}

class Entity implements IEntity {
    public Entity() {
        System.out.println("creating of object");
    }

    @Override
    public void operation() {
        System.out.println("operation");
    }
}

class ServiceProxy implements IEntity {
    private Entity entity;

    @Override
    public void operation() {
        if (entity == null)
            entity = new Entity();

        entity.operation();
    }
}
