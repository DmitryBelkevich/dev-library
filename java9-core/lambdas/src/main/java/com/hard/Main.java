package com.hard;

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
