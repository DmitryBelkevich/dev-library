package com.hard._01_implements_comparator._01_list;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        List<Entity> entities = new ArrayList<>();
//        List<Entity> entities = new LinkedList<>();   // sortable
//        List<Entity> entities = new Vector<>();       // sortable
//        List<Entity> entities = new Stack<>();        // sortable

        entities.add(new Entity(3, "ccc"));
        entities.add(new Entity(2, "aaa"));
        entities.add(new Entity(4, "ddd"));
        entities.add(new Entity(1, "bbb"));

        Collections.sort(entities, new EntityIdComparator());

        System.out.println(entities);
    }
}

/**
 * Model
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
 * comparators
 */

class EntityIdComparator implements Comparator<Entity> {
    @Override
    public int compare(Entity o1, Entity o2) {
        if (o1.getId() > o2.getId())        // ascending
            return 1;
        else if (o1.getId() == o2.getId())  // not sort
            return 0;
        else if (o1.getId() < o2.getId())   // descending
            return -1;

        return 0;
    }

//    @Override
//    public int compare(Entity o1, Entity o2) {
//        Long val1 = new Long(o1.getId());
//        Long val2 = new Long(o2.getId());
//
//        return val1.compareTo(val2);  // + ascending, - descending
//    }
}

class EntityNameComparator implements Comparator<Entity> {
    @Override
    public int compare(Entity o1, Entity o2) {
        String val1 = o1.getName();
        String val2 = o2.getName();

        return val1.compareTo(val2);       // + ascending, - descending
    }
}
