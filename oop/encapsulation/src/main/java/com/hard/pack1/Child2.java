package com.hard.pack1;

public class Child2 {
    private Parent parent;

    public void f() {
//        System.out.println(parent.num1);    // no
        System.out.println(parent.num2);
        System.out.println(parent.num3);
        System.out.println(parent.num4);
    }
}
