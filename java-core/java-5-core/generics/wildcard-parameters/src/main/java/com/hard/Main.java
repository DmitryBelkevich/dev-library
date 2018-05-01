package com.hard;

import java.util.ArrayList;
import java.util.Collection;

public class Main {
    public static void main(String[] args) {
        /**
         * <?>
         * Wildcard Parameters (wildcards)
         * метасимвольные аргументы, подстановочные символы, групповые символы, шаблоны, маски и т.д.
         */
        Collection<Integer> collection = new ArrayList<>();
        collection.add(1);
        collection.add(2);
        collection.add(3);

        print(collection);

        Collection<String> collection2 = new ArrayList<>();
        collection2.add("a");
        collection2.add("b");
        collection2.add("c");

        print(collection2);
    }

    private static void print(Collection<?> collection) {
        for (Object obj : collection)
            System.out.println(obj);
    }
}
