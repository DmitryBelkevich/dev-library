package com.hard._02_composition;

public class Main {
    public static void main(String[] args) {

    }
}

/**
 * Composition
 * relationship: has-a
 */

class SystemUnit {

}

class Monitor {

}

class Computer {
    private SystemUnit systemUnit;
    private Monitor monitor;
}
