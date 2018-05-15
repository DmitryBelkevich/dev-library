package com.hard;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Originator originator = new Originator();
        Caretaker caretaker = new Caretaker();

        // changing state

        originator.setState("State 1");
        System.out.println(originator.getState());

        // saving state

        Memento savedMemento = originator.saveMemento();
        caretaker.addMemento(savedMemento);

        // changing state

        originator.setState("State 2");
        System.out.println(originator.getState());

        // loading state

        Memento loadedMemento = caretaker.getMemento(0);
        originator.loadMemento(loadedMemento);
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
    private List<Memento> mementos = new ArrayList<>();

    public Memento getMemento(int i) {
        return mementos.get(i);
    }

    public void addMemento(Memento memento) {
        mementos.add(memento);
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

    public Memento saveMemento() {
        return new Memento(state);
    }

    public void loadMemento(Memento memento) {
        state = memento.getState();
    }
}
