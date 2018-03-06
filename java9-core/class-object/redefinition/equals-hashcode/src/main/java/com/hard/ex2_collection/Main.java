package com.hard.ex2_collection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

public class Main {
    public static void main(String[] args) {
        boolean result;

        Collection<Entity> entities = new ArrayList<>();
        Entity entity = new Entity("Hello World");
        entities.add(entity);

        result = entities.contains(new Entity("Hello World"));

        System.out.println(result);
    }
}

class Entity {
    private String str;

    public Entity(String str) {
        this.str = str;
    }

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;

        if (!(o instanceof Entity))
            return false;

        Entity entity = (Entity) o;

        return Objects.equals(str, entity.str);
    }
}
