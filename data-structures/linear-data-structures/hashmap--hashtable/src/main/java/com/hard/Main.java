package com.hard;

public class Main {
    public static void main(String[] args) {
        HashTable<Integer> hashTable = new HashTable<>();

        for (int i = 0; i < 10; i++)
            hashTable.add(i);
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

    public HashTable() {
        table = new Node[size];
    }

    private int hashcode(int data) {
        return data % size;
    }

    public void add(int data) {
        int hashcode = this.hashcode(data);

        Node node = new Node();
        node.hashcode = hashcode;
        node.data = data;
        node.next = null;
    }
}
