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
        Node<T> parent = null;
        Node<T> child = root;

        if (child != null) {
            if (data.hashCode() == child.data.hashCode()) {
                root = null;
                return;
            }

            parent = child;

            if (data.hashCode() < parent.data.hashCode()) {
                Node<T> child1 = parent.left;

                if (child1 != null) {
                    if (data.hashCode() == child1.data.hashCode()) {
                        parent.left = null;
                        return;
                    }
                }

                return;
            }

            if (data.hashCode() >= parent.data.hashCode()) {
                Node<T> child2 = parent.right;

                if (child2 != null) {
                    if (data.hashCode() == child2.data.hashCode()) {
                        parent.right = null;
                        return;
                    }
                }

                return;
            }
        }
    }

    public void removeRecursive(Node<T> parent, Node<T> child, T data) {

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
