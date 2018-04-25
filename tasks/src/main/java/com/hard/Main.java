package com.hard;

import java.util.ArrayList;
import java.util.Collection;

public class Main {
    public static void main(String[] args) {
        Collection<String> collection = new ArrayList<>();

        collection.add("aa1");
        collection.add("cc2");
        collection.add("cc1");
        collection.add("aa2");
        collection.add("bb1");

        collection
                .stream()
                .filter(s -> s.startsWith("a"))
                .map(String::toUpperCase)
                .sorted()
                .forEach(System.out::println);
    }
}
