package com.hard._2_advanced._1_abstract;

public class Main {
    public static void main(String[] args) {
        Director director = new Director();

        director.setEntityBuilder(new Entity1Builder());

        Entity entity = director.buildEntity();

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
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

abstract class EntityBuilder {
    protected Entity entity;

    public void createEntity() {
        entity = new Entity();
    }

    public abstract void buildId();

    public abstract void buildName();

    public Entity getEntity() {
        return entity;
    }
}

/**
 * Concrete Builder
 */

class Entity1Builder extends EntityBuilder {
    @Override
    public void buildId() {
        entity.setId(1);
    }

    @Override
    public void buildName() {
        entity.setName("entity1");
    }
}

class Entity2Builder extends EntityBuilder {
    @Override
    public void buildId() {
        entity.setId(2);
    }

    @Override
    public void buildName() {
        entity.setName("entity2");
    }
}

/**
 * Director
 */

class Director {
    private EntityBuilder entityBuilder;

    public void setEntityBuilder(EntityBuilder entityBuilder) {
        this.entityBuilder = entityBuilder;
    }

    public Entity buildEntity() {
        entityBuilder.createEntity();

        entityBuilder.buildId();
        entityBuilder.buildName();

        return entityBuilder.getEntity();
    }
}
