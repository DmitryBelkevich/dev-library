package com.hard.ex1_objects_collection;

import java.util.Objects;

public class Entity {
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
