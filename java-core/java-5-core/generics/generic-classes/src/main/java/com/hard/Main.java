package com.hard;

public class Main {
    public static void main(String[] args) {
        Entity<String> entity = new Entity<String>("Hello World");
    }
}

class Entity<T> {
    private T value;

    public Entity(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }
}
