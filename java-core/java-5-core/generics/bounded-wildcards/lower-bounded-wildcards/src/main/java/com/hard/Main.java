package com.hard;

import java.util.ArrayList;
import java.util.Collection;

public class Main {
    public static void main(String[] args) {
        Collection<? super Integer> collection = new ArrayList<Number>();

        collection.add(1);
        collection.add(2);
        collection.add(3);

        print(collection);
    }

    private static void print(Collection<? super Integer> collection) {
        for (Object obj : collection)
            System.out.println(obj);
    }
}
