package com.hard;

public class Main {
    public static void main(String[] args) {
        List<Object> list = new LinkedList<>();

        list.add(1);
        list.add(2);
        list.add(3);

        System.out.println(list);
    }
}

interface List<T> {
    void add(T data);

    void add(int index, T data);

    void remove(T data);

    void print();
}

class ArrayList<T> implements List<T> {
    private T[] objects;

    @Override
    public void add(T data) {

    }

    @Override
    public void add(int index, T data) {

    }

    @Override
    public void remove(T data) {

    }

    @Override
    public void print() {

    }
}

class LinkedList<T> implements List<T> {
    private class Node<T> {
        private T data;
        private Node<T> next;
        private Node<T> prev;
    }

    private Node<T> first;
    private Node<T> last;

    @Override
    public void add(T data) {

    }

    @Override
    public void add(int index, T data) {

    }

    @Override
    public void remove(T data) {

    }

    @Override
    public void print() {

    }
}
