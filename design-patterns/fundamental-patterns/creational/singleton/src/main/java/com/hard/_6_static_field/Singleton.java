package com.hard._6_static_field;

public class Singleton {
    public static final Singleton instance = new Singleton();
}

/**
 * - eager initialization
 * + multithreading safe
 *
 * - возможно создать дополнительный экземпляр класса: new Singleton()
 */
