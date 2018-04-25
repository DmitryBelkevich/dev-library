package com.hard;

public class Main {
    public static void main(String[] args) {
        Function<Integer, String> intToString = new Function<Integer, String>() {
            @Override
            public String apply(Integer from) {
                return from.toString();
            }
        };

        String result = intToString.apply(10);
        System.out.println(result);
    }
}

interface Function<F, T> {
    T apply(F from);
}
