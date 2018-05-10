package com.hard;

public class Main {
    public static void main(String[] args) {
        Originator originator = new Originator();

        Caretaker caretaker = new Caretaker();

        originator.setState("State 1");
        System.out.println(originator.getState());

        Memento createdMemento = originator.createMemento();
        caretaker.setMemento(createdMemento);

        originator.setState("State 2");
        System.out.println(originator.getState());

        Memento loadedMemento = caretaker.getMemento();
        originator.setMemento(loadedMemento);
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

    public Memento getMemento() {
        return memento;
    }

    public void setMemento(Memento memento) {
        this.memento = memento;
    }
}

/**
 * Originator
 */

class Originator {
    private String state;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Memento createMemento() {
        return new Memento(state);
    }

    public void setMemento(Memento memento) {
        state = memento.getState();
    }
}
