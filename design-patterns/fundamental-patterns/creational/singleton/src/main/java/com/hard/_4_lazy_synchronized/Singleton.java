package com.hard._4_lazy_synchronized;

public class Singleton {
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
 * + lazy initialization
 * + multithreading safe
 * + обрабатка исключительных ситуаций в конструкторе: возможно
 *
 * - низкая производительность. синхронизация полезна только один раз, при первом обращении к getInstance(),
 * после этого синхронизация не нужна и каждый раз, при обращении этому методу, синхронизация просто забирает время
 *
 * (если вызов getInstance() не происходит достаточно часто, то этот метод имеет преимущество перед остальными)
 */
