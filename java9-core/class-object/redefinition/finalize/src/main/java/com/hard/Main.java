package com.hard;

public class Main {
    public static void main(String[] args) {
        for (int i = 0; i < 1000 * 1000; i++)
            new Entity();
    }
}

class Entity {
    public Entity() {
        System.out.println(this + " is creating");
    }

    @Override
    protected void finalize() throws Throwable {
        try {
            System.out.println(this + " is deleting");
        } finally {
            super.finalize();
        }
    }
}
