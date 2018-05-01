package com.hard;

import java.util.ArrayList;
import java.util.Collection;

public class Main {
    public static void main(String[] args) {
        Collection<Integer> collection = new ArrayList<>();

        collection.add(1);
        collection.add(2);
        collection.add(3);

        print(collection);

        Collection<Double> collection2 = new ArrayList<>();

        collection2.add(1.0);
        collection2.add(2.0);
        collection2.add(3.0);

        print(collection2);
    }

    private static void print(Collection<? extends Number> numbers) {
        for (Number number : numbers)
            System.out.println(number);
    }
}
