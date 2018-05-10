package com.hard;

import java.util.ArrayList;
import java.util.Collection;

public class Main {
    public static void main(String[] args) {
        EntityDao entityDao = new EntityDaoImpl();

        // get all
        Collection<Entity> entities = entityDao.getAll();

        // get
        Entity entity1 = entityDao.getById(1);
        Entity entity2 = entityDao.getById(2);
        Entity entity3 = entityDao.getById(3);

        // add
        entityDao.add(new Entity(1, "entity1"));
        entityDao.add(new Entity(2, "entity2"));
        entityDao.add(new Entity(3, "entity3"));

        // update
        entityDao.update(1, new Entity(4, "entity4"));
        entityDao.update(2, new Entity(5, "entity5"));
        entityDao.update(3, new Entity(6, "entity6"));

        // delete
        entityDao.delete(4);
        entityDao.delete(5);
        entityDao.delete(6);

        // print
        for (Entity entity : entities)
            System.out.println(entity);
    }
}

/**
 * Model
 */

class Entity {
    private int id;
    private String name;

    public Entity() {

    }

    public Entity(int id, String name) {
        this.id = id;
        this.name = name;
    }

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
 * Data Access Object
 */

interface EntityDao {
    Collection<Entity> getAll();

    Entity getById(long id);

    void add(Entity entity);

    void update(long id, Entity entity);

    void delete(long id);
}

/**
 * Concrete Data Access Object
 */

class EntityDaoImpl implements EntityDao {
    private Collection<Entity> entities = new ArrayList<>();    // Database

    @Override
    public Collection<Entity> getAll() {
        return entities;
    }

    @Override
    public Entity getById(long id) {
        for (Entity e : entities)
            if (e.getId() == id)
                return e;

        return null;
    }

    @Override
    public void add(Entity entity) {
        entities.add(entity);
    }

    @Override
    public void update(long id, Entity entity) {
        for (Entity e : entities)
            if (e.getId() == id) {
                e.setId(entity.getId());
                e.setName(entity.getName());
            }
    }

    @Override
    public void delete(long id) {
        for (Entity e : entities)
            if (e.getId() == id) {
                entities.remove(e);
                break;
            }
    }
}
