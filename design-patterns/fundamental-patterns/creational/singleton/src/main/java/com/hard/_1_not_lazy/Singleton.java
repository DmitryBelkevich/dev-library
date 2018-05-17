package com.hard._1_not_lazy;

public class Singleton {
    private static Singleton instance = new Singleton();

    private Singleton() {

    }

    public static Singleton getInstance() {
        return instance;
    }
}

/**
 * - eager initialization
 * + multithreading safe
 * - обрабатка исключительных ситуаций в конструкторе: не возможно
 *
 * подходит:
 * многопоточных приложений
 * когда нет опасности возникновения исключительных ситуаций в конструкторе
 * когда нет необходимости ленивой инициализации
 */
