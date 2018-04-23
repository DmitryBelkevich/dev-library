package com.hard._02_open_addressing;

public interface Map<K, V> {
    V get(K key);

    void add(K key, V value);

    void remove(K key);

    int size();

    void print();
}
