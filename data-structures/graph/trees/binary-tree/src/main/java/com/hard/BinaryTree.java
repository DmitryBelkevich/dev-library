package com.hard;

public class BinaryTree<T> implements Tree<T> {
    private class Node<T> {
        private T data;
        private Node<T> left;
        private Node<T> right;
    }

    private Node<T> root;

    @Override
    public void add(T data) {
        Node<T> node = new Node<>();
        node.data = data;
        node.left = null;
        node.right = null;

        if (root != null) {
            addRecursive(root, node);
            return;
        }

        root = node;
    }

    private void addRecursive(Node<T> node, Node<T> node2) {
        if (node2.data.hashCode() < node.data.hashCode()) {
            if (node.left != null) {
                addRecursive(node.left, node2);
                return;
            }

            node.left = node2;

            return;
        }

        if (node2.data.hashCode() >= node.data.hashCode()) {
            if (node.right != null) {
                addRecursive(node.right, node2);
                return;
            }

            node.right = node2;

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

            size += sizeRecursive(root.left);
            size += sizeRecursive(root.right);
        }

        return size;
    }

    public int sizeRecursive(Node<T> node) {
        int size = 0;

        if (node != null) {
            size++;

            size += sizeRecursive(node.left);
            size += sizeRecursive(node.right);
        }

        return size;
    }

    @Override
    public void print() {
        throw new RuntimeException();
    }
}
