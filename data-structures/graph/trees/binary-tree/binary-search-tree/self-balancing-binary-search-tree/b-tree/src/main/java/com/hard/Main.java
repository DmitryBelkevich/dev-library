package com.hard;

public class Main {
    public static void main(String[] args) {
        Tree<Integer> tree = new Tree<>();
    }
}

class Tree<T> {
    private class Node<T> {
        private T[] data;           // n = level - 1
        private Node<T>[] nodes;    // n = level
    }

    private int level;
    private Node<T> root;
}
