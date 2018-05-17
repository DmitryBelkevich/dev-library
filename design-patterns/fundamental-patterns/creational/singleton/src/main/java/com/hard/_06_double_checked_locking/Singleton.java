package com.hard._06_double_checked_locking;

public class Singleton {
    private static volatile Singleton instance;

    private Singleton() {

    }

    public static Singleton getInstance() {
        if (instance == null)
            synchronized (Singleton.class) {
                if (instance == null)
                    instance = new Singleton();
            }

        return instance;
    }
}

/**
 * почти идеальное решение
 *
 * + lazy initialization
 * + multithreading safe
 * + обрабатка исключительных ситуаций в конструкторе: возможно
 *
 * - использование volatile модификатора может привести к проблемам производительности на мультипроцессорных системах
 *
 * Применение:
 * Наиболее распространенный способ
 * в случае, если приложение является многопоточным
 */
