package com.hard._01_separate_chaining;

interface Map<K, V> {
    V get(K key);

    void add(K key, V value);

    void remove(K key);

    void remove(K key, V value);

    int size();

    void print();
}
