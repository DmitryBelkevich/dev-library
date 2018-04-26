package com.hard._04_reference_to_a_constructor;

public class Main {
    public static void main(String[] args) {
        Factory factory = Entity::new;
//        Factory factory = () -> new Entity();

        Entity entity = factory.execute();
        System.out.println(entity);
    }
}

interface Factory {
    Entity execute();
}

class Entity {

}
