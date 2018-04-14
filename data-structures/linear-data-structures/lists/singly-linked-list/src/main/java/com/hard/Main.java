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

    void add(int index, T data);

    void remove(T data);

    void print();
}

class LinkedList<T> implements List<T> {
    private class Node<T> {
        private T data;
        private Node<T> next;
    }

    private Node<T> first;
    private Node<T> last;

    @Override
    public void add(T data) {
        Node<T> node = new Node<>();
        node.data = data;

        if (last == null) {
            first = node;
            last = node;
        } else {
            last.next = node;
            last = node;
        }
    }

    @Override
    public void add(int index, T data) {
        // add to begin:
        Node<T> node = new Node<>();
        node.data = data;

        if (first == null) {
            first = node;
            last = node;
        } else {
            node.next = first;
            first = node;
        }
    }

    @Override
    public void remove(T data) {
        if (first == null)
            return;

        if (first == last) {
            first = null;
            last = null;

            return;
        }

        if (first.data == data) {
            first = first.next;

            return;
        }

        Node<T> iterator = first;

        while (iterator.next != null) {
            if (iterator.next.data == data) {
                if (last == iterator.next)
                    last = iterator;

                iterator.next = iterator.next.next;

                return;
            }

            iterator = iterator.next;
        }
    }

    @Override
    public void print() {
        Node<T> iterator = first;

        while (iterator != null) {
            System.out.println(iterator.data);
            iterator = iterator.next;
        }
    }
}
