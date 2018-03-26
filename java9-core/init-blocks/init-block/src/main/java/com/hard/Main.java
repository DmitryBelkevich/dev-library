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
     * выполняется первым
     */
    static {
        System.out.println("static-init-block");
        str1 = "Hello World";
    }

    /**
     * блок инициализации
     *
     * служит для инициализации нестатических полей
     * выполняется один раз при создании объекта
     * выполняется вторым
     */
    {
        System.out.println("init-block");
        str2 = "Hello World";
    }

    /**
     * выполняется после блоков инициализации
     */
    public Entity() {
        System.out.println("Ctor");
    }
}
