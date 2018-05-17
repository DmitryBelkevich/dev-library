package com.hard;

/**
 * Double checked locking
 */

public class DoubleCheckedLocking {
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
