package com.hard;

public class Main {
    public static void main(String[] args) {
        Function<Integer, String> intToString = new Function<Integer, String>() {
            @Override
            public String apply(Integer t) {
                return t.toString();
            }
        };

        String result = intToString.apply(10);
        System.out.println(result);
    }
}

interface Function<T, R> {
    R apply(T t);
}
