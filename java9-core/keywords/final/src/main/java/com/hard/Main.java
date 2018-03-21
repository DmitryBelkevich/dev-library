package com.hard;

public class Main {
    public static void main(String[] args) {
        final int n = 0;   // 1.1) локальные переменны не могут быть изменены

        final A a = new A(1);   // 1.4) если final переменная содержит ссылку на объект, объект может быть изменен, но переменная всегда будет ссылаться на тот же самый объект (также справедливо для массивов)
        a.setValue(2);
    }
}

final class Entity {    // 3) классы нельзя наследовать
    public final String str;    // 1.2) поля не могут быть изменены

    public Entity() {
        str = "Hello World";    // 4) Переменные final не инициализируются по умолчанию, им необходимо явно присвоить значение при объявлении или в конструкторе
    }

    public final void f1() {  // 2) методы не могут быть переопределены

    }

    public void f2(final int a) { // 1.3) аргументы методов, предназначены только для чтения, при попытке изменения будет ошибка компиляции

    }
}

class A {
    private int value;

    public A(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
