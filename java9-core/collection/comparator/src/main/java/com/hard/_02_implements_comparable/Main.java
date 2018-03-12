package com.hard._02_implements_comparable;

public class Main {
    public static void main(String[] args) {

    }
}

/**
 * Model
 */

class Entity implements Comparable<Entity> {
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

    @Override
    public int compareTo(Entity o) {
        if (id > o.getId())            // ascending
            return 1;
        else if (id == o.getId())   // not sort
            return 0;
        else if (id < o.getId())    // descending
            return -1;

        return 0;
    }
}
