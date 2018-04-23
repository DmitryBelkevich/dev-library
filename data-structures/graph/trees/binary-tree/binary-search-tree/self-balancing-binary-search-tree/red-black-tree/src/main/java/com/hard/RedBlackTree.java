package com.hard;

public class RedBlackTree<T> implements Tree<T> {
    private class Node<T> {
        private static final boolean RED   = false;
        private static final boolean BLACK = true;

        private T data;
        private boolean color = BLACK;
        private Node<T> left;
        private Node<T> right;
    }

    private Node<T> root;
}
