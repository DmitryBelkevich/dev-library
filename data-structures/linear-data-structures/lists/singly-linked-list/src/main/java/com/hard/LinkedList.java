package com.hard;

public class LinkedList<T> implements List<T> {
    private class Node<T> {
        private T data;
        private Node<T> next;
    }

    private Node<T> first;
    private Node<T> last;

    @Override
    public T get(int index) {
        if (size() < index + 1)
            return null;

        Node<T> iterator = first;

        int cycle = 1;
        while (cycle < index + 1) {
            iterator = iterator.next;
            cycle++;
        }

        T data = iterator.data;

        return data;
    }

    @Override
    public void add(T data) {
        Node<T> node = new Node<>();
        node.data = data;
        node.next = null;

        if (first == null) {
            first = node;
            last = node;

            return;
        }

        last.next = node;
        last = node;
    }

    @Override
    public void add(int index, T data) {
        Node<T> node = new Node<>();
        node.data = data;
        node.next = null;

        if (first == null) {
            first = node;
            last = node;

            return;
        }

        if (index + 1 == 1) {
            node.next = first;
            first = node;

            return;
        }

        if (index + 1 <= size()) {
            Node<T> iterator = first;

            int cycle = 1;
            while (cycle < index) {
                iterator = iterator.next;
                cycle++;
            }

            node.next = iterator.next;
            iterator.next = node;

            return;
        }

        last.next = node;
        last = node;
    }

    @Override
    public void remove(int index) {
        if (size() < index + 1)
            return;

        if (first == null)
            return;

        if (first == last) {
            first = null;
            last = null;

            return;
        }

        if (index + 1 == 1) {
            first = first.next;

            return;
        }

        Node<T> iterator = first;

        int cycle = 1;
        while (iterator.next != null) {
            if (cycle + 1 == index + 1) {
                if (last == iterator.next)
                    last = iterator;

                iterator.next = iterator.next.next;

                return;
            }

            iterator = iterator.next;
            cycle++;
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
    public int size() {
        Node<T> iterator = first;

        int size = 0;
        while (iterator != null) {
            iterator = iterator.next;
            size++;
        }

        return size;
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
