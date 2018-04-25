package com.hard;

import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class AccessToPrivateTest {
    public static class FieldTest {
        private class Entity {
            private String str = "Hello World";
        }

        @Test
        public void should_retrieve_access_to_private_field() {
            Entity entity = new Entity();
            Class clazz = entity.getClass();

            Field field = null;
            try {
                field = clazz.getDeclaredField("str");
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            }

            field.setAccessible(true);

            System.out.println("field name: " + field.getName());
            System.out.println("field type: " + field.getType());
            String str = null;
            try {
                str = (String) field.get(entity);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            System.out.println("field instance (value): " + str);
        }
    }

    public static class MethodTest {
        private class Entity {
            private void print(String str) {
                System.out.println(str);
            }
        }

        @Test
        public void should_retrieve_access_to_private_method() {
            Entity entity = new Entity();
            Class clazz = entity.getClass();

            Method method = null;
            try {
                method = clazz.getDeclaredMethod("print", String.class);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }

            method.setAccessible(true);

            try {
                method.invoke(entity, "Hello World");
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }
}
