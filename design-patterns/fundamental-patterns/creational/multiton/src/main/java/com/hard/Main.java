package com.hard;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Multiton multiton1 = Multiton.getInstance(new Entity());
        Multiton multiton2 = Multiton.getInstance(new Entity());
        Multiton multiton3 = Multiton.getInstance(new Entity());

        System.out.println(multiton1);
        System.out.println(multiton2);
        System.out.println(multiton3);
    }
}

class Entity {

}

class Multiton {
    private static Map<Entity, Multiton> instances = new HashMap<>();
    private Entity entity;

    private Multiton(Entity entity) {
        this.entity = entity;
    }

    public static Multiton getInstance(Entity entity) {
        if (!instances.containsKey(entity))
            instances.put(entity, new Multiton(entity));

        Multiton multiton = instances.get(entity);

        return multiton;
    }

    public String toString() {
        return entity.toString();
    }
}
