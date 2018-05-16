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
 * + ленивая инициализация
 *
 * - не работает в многопоточной среде (т.е. подходит только для однопоточных приложений)
 *
 * подходит только для:
 * однопоточных приложений
 */
