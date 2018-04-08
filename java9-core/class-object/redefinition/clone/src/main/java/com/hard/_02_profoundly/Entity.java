package com.hard._02_profoundly;

public class Entity implements Cloneable {
    private int id;
    private String str;

    public Entity(int id, String str) {
        this.id = id;
        this.str = str;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        Entity entity = new Entity(id, new String(str));

        return entity;
    }
}
