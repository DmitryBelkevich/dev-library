package com.hard;

import java.util.ArrayList;
import java.util.Collection;

public class Main {
    public static void main(String[] args) {
        Collection<Entity> entities = new ArrayList<>();

        entities.add(new Entity(1, "entity1"));
        entities.add(new Entity(2, "entity2"));
        entities.add(new Entity(3, "entity3"));
        entities.add(new Entity(4, "entity4"));
        entities.add(new Entity(5, "entity5"));
        entities.add(new Entity(6, "entity6"));
        entities.add(new Entity(7, "entity7"));
        entities.add(new Entity(8, "entity8"));
        entities.add(new Entity(9, "entity9"));
        entities.add(new Entity(10, "entity10"));

        Criteria idRangeCriteria = new IdRangeCriteria(5, 7);
        Criteria nameCriteria = new NameCriteria("entity1");
        Criteria andCriteria = new AndCriteria(idRangeCriteria, nameCriteria);
        Criteria orCriteria = new OrCriteria(idRangeCriteria, nameCriteria);

        Collection<Entity> filteredEntities = orCriteria.meetCriteria(entities);

        for (Entity entity : filteredEntities)
            System.out.println(entity);
    }
}

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
 * Criteria
 */

interface Criteria {
    Collection<Entity> meetCriteria(Collection<Entity> entities);
}

/**
 * Concrete Criteria
 */

class IdRangeCriteria implements Criteria {
    private long minId;
    private long maxId;

    public IdRangeCriteria(long minId, long maxId) {
        this.minId = minId;
        this.maxId = maxId;
    }

    @Override
    public Collection<Entity> meetCriteria(Collection<Entity> entities) {
        Collection<Entity> filteredEntities = new ArrayList<>();

        for (Entity entity : entities)
            if (entity.getId() >= minId && entity.getId() <= maxId)
                filteredEntities.add(entity);

        return filteredEntities;
    }
}

class NameCriteria implements Criteria {
    private String name;

    public NameCriteria(String name) {
        this.name = name;
    }

    @Override
    public Collection<Entity> meetCriteria(Collection<Entity> entities) {
        Collection<Entity> filteredEntities = new ArrayList<>();

        for (Entity entity : entities)
            if (entity.getName().equals(name))
                filteredEntities.add(entity);

        return filteredEntities;
    }
}

class AndCriteria implements Criteria {
    private Criteria criteria1;
    private Criteria criteria2;

    public AndCriteria(Criteria criteria1, Criteria criteria2) {
        this.criteria1 = criteria1;
        this.criteria2 = criteria2;
    }

    @Override
    public Collection<Entity> meetCriteria(Collection<Entity> entities) {
        Collection<Entity> filteredPersonsByCriteria1 = criteria1.meetCriteria(entities);
        Collection<Entity> filteredPersonsByCriteria1AndCriteria2 = criteria2.meetCriteria(filteredPersonsByCriteria1);

        return filteredPersonsByCriteria1AndCriteria2;
    }
}

class OrCriteria implements Criteria {
    private Criteria criteria1;
    private Criteria criteria2;

    public OrCriteria(Criteria criteria1, Criteria criteria2) {
        this.criteria1 = criteria1;
        this.criteria2 = criteria2;
    }

    @Override
    public Collection<Entity> meetCriteria(Collection<Entity> entities) {
        Collection<Entity> filteredPersonsByCriteria1 = criteria1.meetCriteria(entities);
        Collection<Entity> filteredPersonsByCriteria2 = criteria2.meetCriteria(entities);

        for (Entity person : filteredPersonsByCriteria2)
            if(!filteredPersonsByCriteria1.contains(person))
                filteredPersonsByCriteria1.add(person);

        return filteredPersonsByCriteria1;
    }
}
