package com.hard.string._02_Base64;

public class Entity {
    private int id;
    private String str;

    public Entity(int id, String str) {
        this.id = id;
        this.str = str;
    }

    @Override
    public String toString() {
        return "Entity{" +
                "id=" + id +
                ", str='" + str + '\'' +
                '}';
    }
}
