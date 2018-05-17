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
 * - отсутствует возможность обработки исключительных ситуаций (exceptions) во время вызова конструктора
 *
 * подходит:
 * многопоточных приложений
 * когда нет опасности возникновения исключительных ситуаций в конструкторе
 * когда нет необходимости ленивой инициализации
 */
