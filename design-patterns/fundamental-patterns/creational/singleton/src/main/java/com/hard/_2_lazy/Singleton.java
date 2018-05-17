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
 * + есть возможность обрабатывать исключительные ситуации в конструкторе
 * - not multithreading safe
 *
 * подходит только для:
 * однопоточных приложений
 */
