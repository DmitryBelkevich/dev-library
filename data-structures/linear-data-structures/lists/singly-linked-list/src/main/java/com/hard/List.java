package com.hard;

public interface List<T> {
    T get(int index);

    void add(T data);

    void add(int index, T data);

    void remove(int index);

    void remove(T data);

    int size();

    void print();
}
