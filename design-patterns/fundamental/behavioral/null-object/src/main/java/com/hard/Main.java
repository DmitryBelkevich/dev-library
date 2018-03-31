package com.hard;

import java.util.ArrayList;
import java.util.Collection;

public class Main {
    public static void main(String[] args) {
        AbstractEntity entity1 = EntityFactory.getEntity("entity1");
        AbstractEntity entity2 = EntityFactory.getEntity("entity2");
        AbstractEntity entity3 = EntityFactory.getEntity("entity3");
        AbstractEntity entity4 = EntityFactory.getEntity("entity4");

        System.out.println(entity1.getName());
        System.out.println(entity2.getName());
        System.out.println(entity3.getName());
        System.out.println(entity4.getName());
    }
}

/**
 * Abstract Entity
 */

abstract class AbstractEntity {
    protected String name;
    public abstract boolean isNull();
    public abstract String getName();

    @Override
    public String toString() {
        return "AbstractEntity{" +
                "name='" + name + '\'' +
                '}';
    }
}

/**
 * Concrete Entity
 */

class Entity extends AbstractEntity {
    public Entity(String name) {
        this.name = name;
    }

    @Override
    public boolean isNull() {
        return false;
    }

    @Override
    public String getName() {
        return name;
    }
}

class NullEntity extends AbstractEntity {
    @Override
    public boolean isNull() {
        return true;
    }

    @Override
    public String getName() {
        return "not available in database";
    }
}

/**
 * Entity Factory
 */

class EntityFactory {
    public static final Collection<String> names;

    static {
        names = new ArrayList<>();

        names.add("entity1");
        names.add("entity2");
        names.add("entity3");
    }

    public static AbstractEntity getEntity(String name) {
        for (String n : names)
            if (n.equalsIgnoreCase(name))
                return new Entity(name);

        return new NullEntity();
    }
}
