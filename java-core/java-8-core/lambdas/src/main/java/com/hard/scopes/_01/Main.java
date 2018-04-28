package com.hard.scopes._01;

public class Main {
    public static void main(String[] args) {
        int a = 0;

        Listener listener = () -> {
            // set:
//            a = 0;  // not compile
            new Entity().b = 1;
            Entity.c = 2;

            // get:
            System.out.println(a);
            System.out.println(new Entity().b);
            System.out.println(Entity.c);
        };

//        a = 1;  // not compile

        listener.execute();
    }
}

interface Listener {
    void execute();
}

class Entity {
    public int b = 1;
    public static int c = 2;
}
