package com.hard.pack2;

import com.hard.pack1.Parent;

public class Child2 {
    private Parent parent;

    public void f() {
//        System.out.println(parent.num1);    // no
//        System.out.println(parent.num2);    // no
//        System.out.println(parent.num3);    // no
        System.out.println(parent.num4);
    }
}
