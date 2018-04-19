package com.hard;

public class BinaryTree<T> implements Tree<T> {
    private class Node<T> {
        private T data;
        private Node<T> left;
        private Node<T> right;
    }

    private Node<T> root;

    public BinaryTree() {

    }

    @Override
    public void add(T data) {
        throw new RuntimeException();
    }

    @Override
    public void remove(T data) {
        throw new RuntimeException();
    }

    @Override
    public int size() {
        throw new RuntimeException();
    }

    @Override
    public void print() {
        throw new RuntimeException();
    }
}
