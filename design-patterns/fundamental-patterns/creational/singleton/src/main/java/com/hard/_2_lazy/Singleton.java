package com.hard._2_lazy;

public class Singleton {
    private static Singleton instance;

    private Singleton() {

    }

    public static Singleton getInstance() {
        if (instance == null)
            instance = new Singleton();

        return instance;
    }
}

/**
 * + lazy initialization
 * - not multithreading safe
 * + обрабатка исключительных ситуаций в конструкторе: возможно
 *
 * подходит только для:
 * однопоточных приложений
 */
