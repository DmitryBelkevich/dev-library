package com.hard._3_Initialization_on_Demand_Holder;

public class Singleton {
    private Singleton() {

    }

    private static class SingletonHolder {
        private final static Singleton instance = new Singleton();
    }

    public static Singleton getInstance() {
        return SingletonHolder.instance;
    }
}

/**
 * Initialization on Demand Holder
 *
 * + объект инициализируется при первом вызове метода getInstance() (ленивая инициализация)
 *
 * - отсутствует возможность обработки исключительных ситуаций (exceptions) во время вызова конструктора
 *
 * можно использовать:
 * если конструктор класса не вызывает опасений создания исключительных ситуаций
 */
