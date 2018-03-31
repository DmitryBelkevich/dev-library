package com.hard._5_double_checked_locking;

public class Main {
    public static void main(String[] args) {
        Singleton singleton = Singleton.getInstance();
    }
}

class Singleton {
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
 * + почти идеальное решение
 * - использование volatile модификатора может привести к проблемам производительности на мультипроцессорных системах
 *
 * Применение:
 * Наиболее распространенный способ
 * в случае, если приложение является многопоточным
 */
