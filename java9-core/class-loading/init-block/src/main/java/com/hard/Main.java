package com.hard;

public class Main {
    public static void main(String[] args) {

    }
}

class Entity {
    public static String str1;
    private String str2;

    /**
     * статический блок инициализации
     *
     * служит для инициализации статических полей
     * выполняется один раз при создании объекта или при статическом вызове поля
     */
    static {
        str1 = "Hello World";
    }

    /**
     * анонимный блок инициализации
     *
     * служит для инициализации нестатических полей
     * выполняется один раз при создании объекта
     */
    {
        str2 = "Hello World";
    }

    public Entity() {

    }
}
