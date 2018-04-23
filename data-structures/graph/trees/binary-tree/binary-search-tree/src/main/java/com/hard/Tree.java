package com.hard;

interface Tree<T> {
    void add(T data);

    void remove(T data);

    int size();

    void print();

    int depth();
}
