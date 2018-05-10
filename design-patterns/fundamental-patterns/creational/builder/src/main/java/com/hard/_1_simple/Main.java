package com.hard._1_simple;

public class Main {
    public static void main(String[] args) {
        Entity entity = new EntityBuilder()
                .buildId(1)
                .buildName("entity1")
                .build();

        System.out.println(entity);
    }
}

/**
 * Product
 */

class Entity {
    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Entity["
                + "id=" + id
                + ", name=" + name
                + "]";
    }
}

/**
 * Builder
 */

class EntityBuilder {
    private int id = 0;
    private String name = null;

    public EntityBuilder buildId(int id) {
        this.id = id;
        return this;
    }

    public EntityBuilder buildName(String name) {
        this.name = name;
        return this;
    }

    public Entity build() {
        Entity entity = new Entity();

        entity.setId(id);
        entity.setName(name);

        return entity;
    }
}
