package com.hard._1_template;

import java.util.ArrayList;
import java.util.Collection;

public class Main {
    public static void main(String[] args) {
        Subject subject = new Subject();

        subject.addObserver(new ConsoleObserver());
        subject.addObserver(new FileObserver());

        subject.setData("message 1");
        subject.setData("message 2");
    }
}

/**
 * Observer / Subscriber / Listener
 */

interface Observer {
    void update(String data);
}

/**
 * Concrete Observer / Concrete Subscriber / Concrete Listener
 */

class ConsoleObserver implements Observer {
    @Override
    public void update(String data) {
        System.out.println("to console: " + data);
    }
}

class FileObserver implements Observer {
    @Override
    public void update(String data) {
        System.out.println("to file: " + data);
    }
}

/**
 * Subject / Publisher / Component
 */

interface Observable {
    void addObserver(Observer observer);

    void removeObserver(Observer observer);

    void notifyObservers();
}

/**
 * Concrete Subject / Concrete Publisher / Concrete Component
 */

class Subject implements Observable {
    private String data;
    private Collection<Observer> observers = new ArrayList<>();

    public void setData(String data) {
        this.data = data;
        notifyObservers();
    }

    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers)
            observer.update(data);
    }
}
