package com.hard.example;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        StateManager stateManager = new StateManager();
        Saver saver = new Saver();

        // changing state

        stateManager.setState(new State1());
        System.out.println(stateManager.getState());

        // saving state

        File createdFile = stateManager.createFile();
        saver.saveFile(createdFile);

        // changing state

        stateManager.setState(new State2());
        System.out.println(stateManager.getState());

        // loading state

        File loadedFile = saver.getFile(0);
        stateManager.loadFile(loadedFile);
        System.out.println(stateManager.getState());
    }
}

/**
 * State
 */

abstract class State implements Cloneable {
    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}

class State1 extends State {

}

class State2 extends State {

}

/**
 * Memento
 */

class File {
    private final State state;

    public File(State state) {
        this.state = state;
    }

    public State getState() {
        return state;
    }
}

/**
 * Caretaker
 */

class Saver {
    private List<File> files = new ArrayList<>();

    public File getFile(int i) {
        return files.get(i);
    }

    public void saveFile(File file) {
        files.add(file);
    }
}

/**
 * Originator
 */

class StateManager {
    private State state;

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public File createFile() {
        State clonedState = null;
        try {
            clonedState = (State) state.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        return new File(clonedState);
    }

    public void loadFile(File file) {
        state = file.getState();
    }
}
