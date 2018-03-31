package com.hard._1_not_lazy;

public class Main {
    public static void main(String[] args) {
        Singleton singleton = Singleton.getInstance();
    }
}

class Singleton {
    private static Singleton instance = new Singleton();

    private Singleton() {

    }

    public static Singleton getInstance() {
        return instance;
    }
}

/**
 * + работает в многопоточной среде
 *
 * - отсутствие ленивой инициализации
 * - отсутствует возможность обработки исключительных ситуаций (exceptions) во время вызова конструктора
 *
 * подходит:
 * многопоточных приложений
 * нет опасности возникновения исключительных ситуаций в конструкторе
 * нет необходимости ленивой инициализации
 */
