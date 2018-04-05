package com.hard;

public class Main {
    public static void main(String[] args) {

    }
}

/**
 * при переопределении методов возможно изменить модификатор доступа в сторону расширения (package -> protected -> public)
 */

class A {
    void f1() {

    }
}

class B extends A {
    @Override
    protected void f1() {
        super.f1();
    }
}

class C extends B {
    @Override
    public void f1() {
        super.f1();
    }
}
