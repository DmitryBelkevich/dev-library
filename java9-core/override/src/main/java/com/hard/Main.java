package com.hard;

public class Main {
    public static void main(String[] args) {

    }
}

/**
 * 1) при переопределении методов можно изменить модификатор доступа в сторону расширения (package -> protected -> public)
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

/**
 * 2) при переопределении методов можно изменить возвращаемый тип,
 * если выполняется downcasting (понижающее преобразование, преобразование вниз по иеархии)
 */

class D {
    public Object f1() {
        return null;
    }
}

class E extends D {
    @Override
    public Number f1() {
        return null;
    }
}

class F extends E {
    @Override
    public Integer f1() {
        return null;
    }
}

/**
 * 3) при переопределении методов можно изменить имена аргументов
 */

class G {
    public void f1(Object object) {

    }
}

class H extends G {
    @Override
    public void f1(Object entity) {

    }
}
