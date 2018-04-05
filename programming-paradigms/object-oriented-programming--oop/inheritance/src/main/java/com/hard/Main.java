package com.hard;

public class Main {
    public static void main(String[] args) {

    }
}

class A {
    public void f1() {

    }
}

class B extends A {
    @Override
    public void f1() {
        super.f1();
    }
}
