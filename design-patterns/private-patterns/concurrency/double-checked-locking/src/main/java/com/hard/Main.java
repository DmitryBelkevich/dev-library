package com.hard;

public class Main {
    public static void main(String[] args) {
        DoubleCheckedLocking doubleCheckedLocking = new DoubleCheckedLocking();

        Entity entity = doubleCheckedLocking.getEntity();
    }
}

class Entity {

}

/**
 * Double checked locking
 */

class DoubleCheckedLocking {
    private volatile Entity entity = null;

    public Entity getEntity() {
        if (entity == null) {
            synchronized (this) {
                if (entity == null)
                    entity = new Entity();
            }
        }

        return entity;
    }
}
