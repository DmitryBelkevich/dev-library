package com.hard;

import java.util.ArrayList;
import java.util.Collection;

public class Main {
    public static void main(String[] args) {
        EntityService entityService = new EntityService();

        // get all
        Collection<Entity> entities = entityService.getAll();

        // get
        Entity entity1 = entityService.getById(1);
        Entity entity2 = entityService.getById(2);
        Entity entity3 = entityService.getById(3);

        // add
        entityService.add(new Entity(1, "entity1"));
        entityService.add(new Entity(2, "entity2"));
        entityService.add(new Entity(3, "entity3"));

        // update
        entityService.update(1, new Entity(4, "entity4"));
        entityService.update(2, new Entity(5, "entity5"));
        entityService.update(3, new Entity(6, "entity6"));

        // delete
        entityService.delete(4);
        entityService.delete(5);
        entityService.delete(6);

        // print
        for (Entity entity : entities)
            System.out.println(entity);
    }
}

/**
 * Transfer Object / Value Object
 */

class Entity {
    private long id;
    private String name;

    public Entity(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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
        return "Entity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}

/**
 * Business Object
 */

class EntityService {
    private Collection<Entity> entities = new ArrayList<>();  // database

    public Collection<Entity> getAll() {
        return entities;
    }

    public Entity getById(long id) {
        for (Entity entity : entities)
            if (entity.getId() == id)
                return entity;

        return null;
    }

    public void add(Entity entityVO) {
        entities.add(entityVO);
    }

    public void update(long id, Entity entity) {
        for (Entity e : entities)
            if (e.getId() == id) {
                e.setId(entity.getId());
                e.setName(entity.getName());
            }
    }

    public void delete(long id) {
        for (Entity e : entities)
            if (e.getId() == id) {
                entities.remove(e);
                break;
            }
    }
}
