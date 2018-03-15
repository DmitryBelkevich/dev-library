package com.hard;

public class Main {
    public static void main(String[] args) {
        List<Object> list = new LinkedList<>();

        list.add(1);
        list.add(2);
        list.add(3);

        list.print();
    }
}

interface List<T> {
    void add(T data);

    void addBegin(T data);

    void remove(T data);

    void print();
}

class LinkedList<T> implements List<T> {
    private class Node<T> {
        private T data;
        private Node<T> prev;
        private Node<T> next;
    }

    private Node<T> first;
    private Node<T> last;

    @Override
    public void add(T data) {

    }

    @Override
    public void addBegin(T data) {

    }

    @Override
    public void remove(T data) {

    }

    @Override
    public void print() {

    }
}
