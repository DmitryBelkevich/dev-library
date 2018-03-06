package com.hard.ex1;

import java.util.Objects;

public class Main {
    public static void main(String[] args) {
        boolean result;

        Entity entity1 = new Entity("Hello World");
        Entity entity2 = new Entity("Hello World");

        result = entity1.equals(entity2);
        System.out.println(result); // true - все поля объекта равны
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
