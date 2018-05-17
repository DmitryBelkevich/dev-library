package com.hard._07_Initialization_on_Demand_Holder;

public class Singleton {
    private Singleton() {

    }

    private static class SingletonHolder {
        private static final Singleton instance = new Singleton();
    }

    public static Singleton getInstance() {
        return SingletonHolder.instance;
    }
}

/**
 * Initialization on Demand Holder:
 *
 * + lazy initialization
 * + multithreading safe
 * - обрабатка исключительных ситуаций в конструкторе: не возможно
 *
 * можно использовать:
 * если конструктор класса не вызывает опасений создания исключительных ситуаций
 */
