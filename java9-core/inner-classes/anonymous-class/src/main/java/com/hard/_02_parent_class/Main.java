package com.hard._02_parent_class;

public class Main {
    public static void main(String[] args) {
        C1 c1 = new C1() {
            @Override
            public String getStr() {
                return super.getStr() + " (override)";
            }
        };

        c1.getStr();
    }
}

class C1 {
    public String getStr() {
        return "Hello World";
    }
}
