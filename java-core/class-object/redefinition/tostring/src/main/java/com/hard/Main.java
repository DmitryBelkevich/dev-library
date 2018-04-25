package com.hard;

public class Main {
    public static void main(String[] args) {
        System.out.println(new Entity("Hello World"));
    }
}

class Entity {
    private String str;

    public Entity(String str) {
        this.str = str;
    }

    @Override
    public String toString() {
        return "Entity{" +
                "str='" + str + '\'' +
                '}';
    }
}
