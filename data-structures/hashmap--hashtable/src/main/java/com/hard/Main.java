package com.hard;

public class Main {
    public static void main(String[] args) {
        HashTable<Integer> hashTable = new HashTable<>();
    }
}

class HashTable<T> {
    private int size = 4;

    private class Node<T> {
        private int hashcode;
        private T data;
        private Node<T> next;
    }

    private Node<T>[] table;
}
