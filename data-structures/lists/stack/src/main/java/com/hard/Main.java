package com.hard;

public class Main {
    public static void main(String[] args) {
        Stack<Object> stack = new ArrayStack<>();

        stack.push(1);
        stack.push(2);
        stack.push(3);

        System.out.println(stack.pop());
    }
}

interface Stack<T> {
    void push(T data);

    T pop();
}

class ArrayStack<T> implements Stack<T> {
    private T[] objects;

    public void push(T data) {

    }

    public T pop() {
        return null;
    }
}

class LinkedStack<T> implements Stack<T> {
    private class Node<T> {
        private T data;
        private Node<T> prev;
    }

    private Node<T> top;

    public void push(T data) {

    }

    public T pop() {
        return null;
    }
}
