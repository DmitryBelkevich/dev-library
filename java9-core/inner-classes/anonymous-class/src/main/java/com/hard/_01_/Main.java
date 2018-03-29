package com.hard._01_;

public class Main {
    public static void main(String[] args) {
        I i = new I() {
            @Override
            public String getStr() {
                return "Hello World";
            }
        };

        i.getStr();
    }
}

interface I {
    String getStr();
}
