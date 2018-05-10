package com.hard._2_advanced._2_interface;

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

interface EntityBuilder {
    void createEntity();

    void buildId();

    void buildName();

    Entity getEntity();
}

/**
 * Concrete Builder
 */

class Entity1Builder implements EntityBuilder {
    private Entity entity;

    @Override
    public void createEntity() {
        entity = new Entity();
    }

    @Override
    public void buildId() {
        entity.setId(1);
    }

    @Override
    public void buildName() {
        entity.setName("entity1");
    }

    @Override
    public Entity getEntity() {
        return entity;
    }
}

class Entity2Builder implements EntityBuilder {
    private Entity entity;

    @Override
    public void createEntity() {
        entity = new Entity();
    }

    @Override
    public void buildId() {
        entity.setId(2);
    }

    @Override
    public void buildName() {
        entity.setName("entity2");
    }

    @Override
    public Entity getEntity() {
        return entity;
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
