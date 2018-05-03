package com.hard;

public class Main {
    public static void main(String[] args) {
        B obj = new B();

        System.out.println(execute(obj));
    }

    private static String execute(A obj) {
        return obj.get(); // будет вызван метод get() из экземпляра класса B
    }
}

class A {
    public String get() { // final - установит раннее связывание
        return "A";
    }
}

class B extends A {
    @Override
    public String get() {
        return "B";
    }
}

/**
 * раннее связывание
 * во время компиляции указывается, метод какого класса будет вызван в методе: String execute(A obj), т.е. метод экземпляра класса A
 *
 * позднее связывание
 * во время компиляции не указывается, метод какого класса будет вызван в методе: String execute(A obj),
 * и во время выполнения будет производиться поиск метода в объекте класса и после вызов её
 */
