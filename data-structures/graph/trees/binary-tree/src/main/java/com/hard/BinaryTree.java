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

            return;
        }

        addRecursive(root, node);
    }

    private void addRecursive(Node<T> root, Node<T> node) {
        if (node.data.hashCode() < root.data.hashCode()) {
            if (root.left != null) {
                addRecursive(root.left, node);
                return;
            }

            root.left = node;

            return;
        }

        if (node.data.hashCode() >= root.data.hashCode()) {
            if (root.right != null) {
                addRecursive(root.right, node);
                return;
            }

            root.right = node;

            return;
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
            Node<T> node = root;

            size++;

            if (root.left != null) {
                Node<T> node2 = node.left;

                size++;
            }

            return size;
        }

        return size;
    }

    @Override
    public void print() {
        throw new RuntimeException();
    }
}
