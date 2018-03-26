package com.hard;

public class Main {
    public static void main(String[] args) {

    }
}

abstract class Entity {
    public static String str;

    /**
     * статический блок служит для инициализации статических полей
     * выполняется до выполнения конструктора
     */

    static {
        System.out.println("static");
        str = "Hello World";
    }

    public Entity() {
        System.out.println("Ctor");
    }

    /**
     * статические методы не могут переопределяться в классах-наследниках
     */

    /**
     * статические методы могут перегружаться нестатическими
     */

    public static void f1() {
        System.out.println("f1");
    }

    public void f1(int a) {
        System.out.println("f1");
    }

    /**
     * нестатические методы могут перегружаться статическими
     */

    public void f2() {
        System.out.println("f2");
    }

    public static void f2(int a) {
        System.out.println("f2");
    }
}
