package com.hard;

public class BinarySearchTree<T> implements Tree<T> {
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
        if (root != null) {
            if (data.hashCode() == root.data.hashCode()) {
                root = null;
                return;
            }

            if (data.hashCode() < root.data.hashCode()) {
                if (root.left != null) {
                    if (data.hashCode() == root.left.data.hashCode()) {
                        root.left = null;
                        return;
                    }

                    removeRecursive(root.left, data);
                    return;
                }

                return;
            }

            if (data.hashCode() >= root.data.hashCode()) {
                if (root.right != null) {
                    if (data.hashCode() == root.right.data.hashCode()) {
                        root.right = null;
                        return;
                    }

                    removeRecursive(root.right, data);
                    return;
                }

                return;
            }
        }
    }

    private void removeRecursive(Node<T> node, T data) {
        if (data.hashCode() < node.data.hashCode()) {
            if (node.left != null) {
                if (data.hashCode() == node.left.data.hashCode()) {
                    node.left = null;
                    return;
                }

                removeRecursive(node.left, data);
                return;
            }

            return;
        }

        if (data.hashCode() >= node.data.hashCode()) {
            if (node.right != null) {
                if (data.hashCode() == node.right.data.hashCode()) {
                    node.right = null;
                    return;
                }

                removeRecursive(node.right, data);
                return;
            }

            return;
        }
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

    private int sizeRecursive(Node<T> node) {
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

    @Override
    public int depth() {
        int depth = 0;

        if (root != null) {
            depth++;

            int leftDepth = depthRecursive(root.left);
            int rightDepth = depthRecursive(root.right);

            depth += Math.max(leftDepth, rightDepth);
        }

        return depth;
    }

    private int depthRecursive(Node<T> node) {
        int depth = 0;

        if (node != null) {
            depth++;

            int leftDepth = depthRecursive(node.left);
            int rightDepth = depthRecursive(node.right);

            depth += Math.max(leftDepth, rightDepth);
        }

        return depth;
    }

    @Override
    public boolean isBalanced() {
        if (root == null)
            return true;

        if (root != null)
            if (isBalancedRecursive(root.left) && isBalancedRecursive(root.right))
                return true;

        return false;
    }

    private boolean isBalancedRecursive(Node<T> node) {
        if (node == null)
            return true;

        if (node != null)
            if (isBalancedRecursive(node.left) && isBalancedRecursive(node.right))
                return true;

        return false;
    }
}
