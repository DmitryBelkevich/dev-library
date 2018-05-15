package com.hard.example;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
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

abstract class State {

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
        State clonedState = (State) clone(state);

        return new File(clonedState);
    }

    public void loadFile(File file) {
        state = file.getState();
    }

    private Object clone(Object obj) {
        try {
            Class<?> clazz = obj.getClass();
            Object clone = clazz.newInstance();

            Field[] fields = clazz.getDeclaredFields();

            for (Field field : fields) {
                field.setAccessible(true);
                if (field.get(obj) == null || Modifier.isFinal(field.getModifiers())) {
                    continue;
                }

                if (field.getType().isPrimitive()
                        || field.getType().equals(String.class)
                        || field.getType().getSuperclass().equals(Number.class)
                        || field.getType().equals(Boolean.class)) {
                    field.set(clone, field.get(obj));
                } else {
                    Object childObj = field.get(obj);

                    if (childObj == obj) {
                        field.set(clone, clone);
                    } else {
                        field.set(clone, clone(field.get(obj)));
                    }
                }
            }

            return clone;
        } catch (Exception e) {
            return null;
        }
    }
}
