package com.hard;

import org.junit.Test;

public class GetClass {
    private class Entity {
    }

    @Test
    public void should_retrieve_class_from_instance() {
        // вызов из экземпляра
        Entity entity = new Entity();
        Class clazz = entity.getClass();

        System.out.println(clazz);
    }

    @Test
    public void should_retrieve_class_from_static_variable_class() {
        // вызов из статической переменной class, которая есть у каждого класса
        Class<Entity> clazz = Entity.class;

        System.out.println(clazz);
    }

    @Test
    public void should_retrieve_class_via_forName() {
        // вызов при помощи статического метода: Class.forName(String className)
        Class<?> clazz = null;
        try {
            clazz = Class.forName("com.hard.GetClass$Entity");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        System.out.println(clazz);
    }
}
