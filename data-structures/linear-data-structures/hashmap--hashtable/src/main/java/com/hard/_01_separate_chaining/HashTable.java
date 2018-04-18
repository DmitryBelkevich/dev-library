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
    }

    private int hashcode(K key) {
        int hashcode = key.hashCode();

        return hashcode % size;
    }

    @Override
    public V get(K key) {
        int hashcode = hashcode(key);

        Node<K, V> iterator = table.get(hashcode);

        while (iterator != null) {
            if (iterator.key == key)
                return iterator.value;

            iterator = iterator.next;
        }

        return null;
    }

    @Override
    public void add(K key, V value) {
        Node<K, V> node = new Node<>();

        node.key = key;
        node.value = value;
        node.next = null;

        int hashcode = hashcode(key);

        Node<K, V> iterator = table.get(hashcode);

        if (iterator == null) {
            table.set(hashcode, node);

            return;
        }

        if (iterator != null) {
            while (iterator.next != null) {
                iterator = iterator.next;
            }

            iterator.next = node;
        }
    }

    @Override
    public void remove(K key) {
        int hashcode = hashcode(key);

        Node<K, V> iterator = table.get(hashcode);

        if (iterator != null) {
            if (iterator.key == key) {
                table.set(hashcode, iterator.next);
            }
        }
    }

    @Override
    public int size() {
        int sum = 0;

        for (int i = 0; i < table.size(); i++) {
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
        for (int i = 0; i < table.size(); i++) {
            Node<K, V> iterator = table.get(i);

            while (iterator != null) {
                System.out.println(iterator.key + ":" + iterator.value);
                iterator = iterator.next;
            }
        }
    }
}
