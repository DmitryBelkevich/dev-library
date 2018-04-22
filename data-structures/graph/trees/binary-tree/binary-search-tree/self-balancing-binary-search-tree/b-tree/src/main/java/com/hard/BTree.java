package com.hard;

class BTree<T> implements Tree<T> {
    private class Node<T> {
        private T[] data;           // n = level - 1
        private Node<T>[] nodes;    // n = level
    }

    private int level;
    private Node<T> root;
}
