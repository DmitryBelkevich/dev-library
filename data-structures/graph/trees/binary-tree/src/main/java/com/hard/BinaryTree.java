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
            size++;

            if (root.left != null) {
                size++;
                size = sizeRecursive(root.left, size);
            }

            if (root.right != null) {
                size++;
                size = sizeRecursive(root.right, size);
            }
        }

        return size;
    }

    public int sizeRecursive(Node<T> node, int size) {
        if (node != null) {
            if (node.left != null) {
                size++;
                size = sizeRecursive(node.left, size);
            }

            if (node.right != null) {
                size++;
                size = sizeRecursive(node.right, size);
            }
        }

        return size;
    }

    @Override
    public void print() {
        throw new RuntimeException();
    }
}
