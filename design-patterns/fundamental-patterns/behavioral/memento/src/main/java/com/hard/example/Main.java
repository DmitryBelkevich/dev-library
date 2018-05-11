package com.hard.example;

public class Main {
    public static void main(String[] args) {
        StateManager stateManager = new StateManager();

        Saver saver = new Saver();

        stateManager.setState(new State1());
        System.out.println(stateManager.getState());

        File createdFile = stateManager.createFile();
        saver.saveFile(createdFile);

        stateManager.setState(new State2());
        System.out.println(stateManager.getState());

        File loadedFile = saver.getFile();
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
    private File file;

    public File getFile() {
        return file;
    }

    public void saveFile(File file) {
        this.file = file;
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
