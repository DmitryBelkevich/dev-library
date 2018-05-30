package com.hard._01_string._02_Base64;

import java.io.Serializable;

public class Entity implements Serializable {
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
