package com.hard;

public class Main {
    public static void main(String[] args) {
        Originator originator = new Originator();

        Caretaker caretaker = new Caretaker();

        originator.setState("State 1");
        System.out.println(originator.getState());

        caretaker.setMemento(originator.createMemento());

        originator.setState("State 2");
        System.out.println(originator.getState());

        originator.setMemento(caretaker.getMemento());
        System.out.println(originator.getState());
    }
}

/**
 * Memento
 */

class Memento {
    private final String state;

    public Memento(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }
}

/**
 * Caretaker
 */

class Caretaker {
    private Memento memento;

    public void setMemento(Memento memento) {
        this.memento = memento;
    }

    public Memento getMemento() {
        return memento;
    }
}

/**
 * Originator
 */

class Originator {
    private String state;

    public void setState(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }

    public Memento createMemento() {
        return new Memento(state);
    }

    public void setMemento(Memento memento) {
        state = memento.getState();
    }
}