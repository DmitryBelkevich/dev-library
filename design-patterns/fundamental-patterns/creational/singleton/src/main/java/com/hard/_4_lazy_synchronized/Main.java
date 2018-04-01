package com.hard._4_lazy_synchronized;

public class Main {
    public static void main(String[] args) {
        Singleton singleton = Singleton.getInstance();
    }
}

class Singleton {
    private static Singleton instance;

    private Singleton() {

    }

    public static synchronized Singleton getInstance() {
        if (instance == null)
            instance = new Singleton();

        return instance;
    }
}

/**
 * + ленивая инициализация
 * + работает в многопоточной среде
 * + есть возможность обрабатывать исключительные ситуации в конструкторе
 *
 * - синхронизация полезна только один раз, при первом обращении к getInstance(),
 * после этого синхронизация не нужна и каждый раз, при обращении этому методу, синхронизация просто забирает время
 *
 * (если вызов getInstance() не происходит достаточно часто, то этот метод имеет преимущество перед остальными)
 */
