package com.hard;

public class Main {
    public static void main(String[] args) {
        Entity entity1 = new Entity("Hello World");
        Entity entity2 = null;
        try {
            entity2 = (Entity) entity1.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        System.out.println(entity1);
        System.out.println(entity2);
    }
}

class Entity implements Cloneable {
    private String str;

    public Entity(String str) {
        this.str = str;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
