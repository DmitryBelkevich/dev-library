package com.hard._01_separate_chaining;

import java.util.ArrayList;
import java.util.List;

class HashTable<K, V> implements Map<K, V> {
    private int size = (int) Math.pow(2, 3);
    private List<Node<K, V>> table;

    private class Node<K, V> {
//        private int hashcode;
        private K key;
        private V value;
        private Node<K, V> next;
    }

    public HashTable() {
        table = new ArrayList<>();

        for (int i = 0; i < size; i++)
            table.add(null);

        Node<K, V> node1 = new Node<>();
        node1.key = (K) "a";
        node1.value = (V) "aaa";

        Node<K, V> node2 = new Node<>();
        node2.key = (K) "b";
        node2.value = (V) "bbb";

        node1.next = node2;

        table.set(0, node1);
        table.set(1, node1);
        table.set(2, node1);
        table.set(3, node1);
        table.set(4, node1);
        table.set(5, node1);
        table.set(6, node1);
        table.set(7, node1);
    }

    private int hashcode(K key) {
        int hashcode = key.hashCode();

        return hashcode % size;
    }

    @Override
    public V get(K key) {
        throw new RuntimeException();
//        return null;
    }

    @Override
    public void add(K key, V value) {
        throw new RuntimeException();
    }

    @Override
    public void remove(K key) {
        throw new RuntimeException();
    }

    @Override
    public void remove(K key, V value) {
        throw new RuntimeException();
    }

    @Override
    public int size() {
        int sum = 0;

        for (int i = 0; i < table.size(); i++)
            if (table.get(i) != null) {
                Node<K, V> iterator = table.get(i);

                while (iterator != null) {
                    sum++;
                    iterator = iterator.next;
                }
            }

        return sum;
    }

    @Override
    public void print() {
        throw new RuntimeException();
    }

////    @Override
//    public void add(int data) {
//        int hashcode = this.hashcode(data);
//
//        Node node = new Node();
////        node.hashcode = hashcode;
//        node.data = data;
//        node.next = null;
//    }
}
