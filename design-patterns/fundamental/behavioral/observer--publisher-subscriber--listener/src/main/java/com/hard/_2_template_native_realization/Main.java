package com.hard._2_template_native_realization;

import java.util.Observable;
import java.util.Observer;

public class Main {
    public static void main(String[] args) {
        Observable subject = new Subject();

        subject.addObserver(new ConsoleObserver());
        subject.addObserver(new FileObserver());

        subject.notifyObservers("message 1");
        subject.notifyObservers("message 2");
    }
}

/**
 * Concrete Observer / Concrete Subscriber / Concrete Listener
 */

class ConsoleObserver implements Observer {
    @Override
    public void update(Observable o, Object arg) {
        System.out.println("to console: " + arg);
    }
}

class FileObserver implements Observer {
    @Override
    public void update(Observable o, Object arg) {
        System.out.println("to file: " + arg);
    }
}

/**
 * Concrete Subject / Concrete Publisher / Concrete Component
 */

class Subject extends Observable {
    @Override
    public void notifyObservers(Object arg) {
        super.setChanged();
        super.notifyObservers(arg);
    }
}
