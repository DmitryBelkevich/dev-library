package com.hard.pack2;

import com.hard.pack1.Parent;

public class Child1 extends Parent {
    public void f() {
//        System.out.println(this.num1);    // no
//        System.out.println(this.num2);    // no
        System.out.println(this.num3);
        System.out.println(this.num4);
    }
}
