package com.hard._01_separate_chaining;

import java.util.ArrayList;
import java.util.Collection;

class HashTable<K, V> implements Map<K, V> {
    private int size = (int) Math.pow(2, 3);
    private Collection<Node<K, V>> table;

    private class Node<K, V> {
//        private int hashcode;
        private K key;
        private V value;
        private Node<K, V> next;
    }

    public HashTable() {
        table = new ArrayList<>();
    }

    private int hashcode(int data) {
        return data % size;
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
        throw new RuntimeException();
//        return 0;
    }

    @Override
    public void print() {
        throw new RuntimeException();
    }
}
