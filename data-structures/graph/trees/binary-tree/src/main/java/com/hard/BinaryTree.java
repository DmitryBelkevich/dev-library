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
        Node<T> node = new Node<>();
        node.data = data;
        node.left = null;
        node.right = null;

        if (root == null) {
            root = node;
        }
    }

    @Override
    public void remove(T data) {
        throw new RuntimeException();
    }

    @Override
    public int size() {
        int size = 0;

        if (root != null) {
//            Node<T> node = root;

            size++;

            return size;
        }

        return size;
    }

    @Override
    public void print() {
        throw new RuntimeException();
    }
}
