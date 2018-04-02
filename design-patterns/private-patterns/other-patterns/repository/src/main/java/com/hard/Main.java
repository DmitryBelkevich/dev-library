package com.hard;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        EntityRepository entityRepository = new EntityRepositoryImpl();

        // add
        entityRepository.add(new Entity(1, "entity1"));
        entityRepository.add(new Entity(2, "entity2"));
        entityRepository.add(new Entity(3, "entity3"));
        entityRepository.add(new Entity(4, "entity4"));
        entityRepository.add(new Entity(5, "entity5"));

        // create specification
        Specification specification = new EntitySpecificationByIdRangeAndByName(
                new EntitySpecificationByIdRange(2, 8),
                new EntitySpecificationByName("entity3")
        );

        // get or delete all by specification
        Collection<Entity> entities = entityRepository.getAll(specification);

        for (Entity entity : entities)
            System.out.println(entity);
    }
}

/**
 * Model
 */

class Entity {
    private long id;
    private String name;

    public Entity() {

    }

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
 * Specification
 */

interface Specification<T> {
    boolean specified(T o);
}

/**
 * Concrete Specification
 */

class EntitySpecificationByIdRange implements Specification<Entity> {
    private long minId;
    private long maxId;

    public EntitySpecificationByIdRange(long minId, long maxId) {
        this.minId = minId;
        this.maxId = maxId;
    }

    @Override
    public boolean specified(Entity entity) {
        if (entity.getId() >= minId && entity.getId() <= maxId)
            return true;

        return false;
    }
}

class EntitySpecificationByName implements Specification<Entity> {
    private String name;

    public EntitySpecificationByName(String name) {
        this.name = name;
    }

    @Override
    public boolean specified(Entity entity) {
        if (entity.getName().equals(name))
            return true;

        return false;
    }
}

class EntitySpecificationByIdRangeAndByName implements Specification<Entity> {
    private EntitySpecificationByIdRange entitySpecificationByIdRange;
    private EntitySpecificationByName entitySpecificationByName;

    public EntitySpecificationByIdRangeAndByName(EntitySpecificationByIdRange entitySpecificationByIdRange, EntitySpecificationByName entitySpecificationByName) {
        this.entitySpecificationByIdRange = entitySpecificationByIdRange;
        this.entitySpecificationByName = entitySpecificationByName;
    }

    @Override
    public boolean specified(Entity entity) {
        return entitySpecificationByIdRange.specified(entity) && entitySpecificationByName.specified(entity);
    }
}

/**
 * Repository
 */

interface EntityRepository {
    Collection<Entity> getAll(Specification specification);

    Entity getById(long id);

    void add(Entity entity);

    void add(Collection<Entity> entities);

    void update(long id, Entity entity);

    void delete(long id);

    void delete(Specification specification);
}

/**
 * Concrete Repository
 */

class EntityRepositoryImpl implements EntityRepository {
    private List<Entity> entities = new ArrayList<>();    // Database

    @Override
    public Collection<Entity> getAll(Specification specification) {
        Collection<Entity> filteredEntities = new ArrayList<>();

        for (Entity entity : entities)
            if (specification.specified(entity))
                filteredEntities.add(entity);

        return filteredEntities;
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
    public void add(Collection<Entity> entities) {
        this.entities.addAll(entities);
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

    @Override
    public void delete(Specification specification) {
        Collection<Entity> filteredEntities = new ArrayList<>();

        for (Entity entity : entities)
            if (specification.specified(entity))
                filteredEntities.add(entity);

        entities.removeAll(filteredEntities);
    }
}
