package com.hard;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Aggregate<Integer> arr = new Aggregate1<>();

        for (int i = 0; i < 10; i++)
            arr.add(i);

        Iterator<Integer> iterator = arr.iterator();
        while (iterator.hasNext()) {
            int val = iterator.next();
            System.out.println(val);
        }
    }
}

/**
 * Iterator
 */

interface Iterator<T> {
    boolean hasNext();

    T next();
}

/**
 * Aggregate / Container
 */

interface Aggregate<T> {
    void add(T value);

    void remove(int index);

    Iterator<T> iterator();
}

/**
 * Concrete Aggregate / Concrete Container
 */

class Aggregate1<T> implements Aggregate<T> {
    private List<T> arr = new ArrayList<>();

    @Override
    public void add(T value) {
        arr.add(value);
    }

    @Override
    public void remove(int index) {
        arr.remove(index);
    }

    @Override
    public Iterator<T> iterator() {
        return new IteratorAggregate1();
    }

    /**
     * Concrete Iterator
     */

    private class IteratorAggregate1 implements Iterator<T> {
        private int index = 0;

        @Override
        public boolean hasNext() {
            if (index < arr.size())
                return true;

            return false;
        }

        @Override
        public T next() {
            return arr.get(index++);
        }
    }
}
