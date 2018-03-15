package com.hard;

public class Main {
    public static void main(String[] args) {
        List<Object> vector = new Vector<>();

        vector.add(1);
        vector.add(2);
        vector.add(3);

        System.out.println(vector);
    }
}

interface List<T> {
    void add(T data);
}

class Vector<T> implements List<T> {
    private Object[] objects;

    @Override
    public void add(T data) {

    }
}
