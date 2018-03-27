package com.hard;

public class Main {
    public static void main(String[] args) {
        // 1 - вызов из объекта
        Entity entity = new Entity();
        Class clazz = entity.getClass();

        // 2 - вызов из статической переменной class, которая есть у каждого класса
//        Class<Entity> clazz = Entity.class;

        // 3
//        Class<?> clazz = null;
//        try {
//            clazz = Class.forName("com.hard.Entity");
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }

        System.out.println();
    }
}

class Entity {
    private String str = "Hello World";
}
