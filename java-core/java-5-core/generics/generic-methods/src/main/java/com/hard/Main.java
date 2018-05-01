package com.hard;

public class Main {
    public static void main(String[] args) {
        Entity entity = new Entity();

        entity.execute("Hello World");
    }
}

class Entity {
    public <T> void execute(T value) {
        System.out.println(value);
    }
}
