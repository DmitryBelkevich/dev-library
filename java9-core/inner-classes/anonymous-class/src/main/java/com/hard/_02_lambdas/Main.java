package com.hard._02_lambdas;

public class Main {
    public static void main(String[] args) {
        Entity entity = (str) -> str;

        String result = entity.execute("Hello World");

        System.out.println(result);
    }
}

interface Entity {
    String execute(String str);
}
