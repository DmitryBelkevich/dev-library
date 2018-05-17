package com.hard._5_double_checked_locking;

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
 * + есть возможность обрабатывать исключительные ситуации в конструкторе
 *
 * - использование volatile модификатора может привести к проблемам производительности на мультипроцессорных системах
 *
 * Применение:
 * Наиболее распространенный способ
 * в случае, если приложение является многопоточным
 */
