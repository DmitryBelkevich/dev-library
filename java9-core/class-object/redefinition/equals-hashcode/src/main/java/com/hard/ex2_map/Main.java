package com.hard.ex2_map;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Main {
    public static void main(String[] args) {
        Map<Entity, Object> entities = new HashMap<>();

        entities.put(new Entity("Hello World"), new Object());

        Object object = entities.get(new Entity("Hello World"));

        System.out.println(object);
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

    @Override
    public int hashCode() {
        return Objects.hash(str);
    }
}
