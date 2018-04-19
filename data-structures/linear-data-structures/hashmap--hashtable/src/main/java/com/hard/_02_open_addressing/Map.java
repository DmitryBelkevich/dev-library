package com.hard._02_open_addressing;

interface Map<K, V> {
    V get(K key);

    void add(K key, V value);

    void remove(K key);

    int size();

    void print();
}
