package com.hard._01_separate_chaining;

public interface Map<K, V> {
    V get(K key);

    void add(K key, V value);

    void remove(K key);

    int size();

    void print();
}
