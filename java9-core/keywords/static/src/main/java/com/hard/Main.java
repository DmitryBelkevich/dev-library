package com.hard;

public class Main {
    public static void main(String[] args) {

    }
}

class Entity {
    public static String str;

    static {
        System.out.println("static");
        str = "Hello World";
    }

    public Entity() {
        System.out.println("Ctor");
    }
}
