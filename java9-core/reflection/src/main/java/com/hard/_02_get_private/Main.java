package com.hard._02_get_private;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Main {
    public static void main(String[] args) throws IllegalAccessException, InvocationTargetException {
        Entity entity = new Entity();
        Class clazz = entity.getClass();

        Field[] fields = clazz.getDeclaredFields();

        for (Field field : fields) {
            field.setAccessible(true);

            System.out.println("field :" + field.getName());
            System.out.println("type: " + field.getType());
            System.out.println("value: " + field.get(entity));
        }

        Method[] methods = clazz.getDeclaredMethods();

        for (Method method : methods) {
            method.setAccessible(true);

            method.invoke(entity, "Hello World");
        }
    }
}

class Entity {
    private String str = "Hello World";

    private void f(String str) {
        System.out.println("f(String str): " + str);
    }
}
