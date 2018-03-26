package com.hard;

public class Main {
    public static void main(String[] args) {
        Entity entity = new Entity();

        Class clazz = entity.getClass();
    }
}

class Entity {
    private String str = "Hello World";
}
