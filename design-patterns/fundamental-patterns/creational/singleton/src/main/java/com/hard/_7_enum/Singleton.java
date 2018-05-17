package com.hard._7_enum;

public enum Singleton {
    instance
}

/**
 * - eager initialization
 * + multithreading safe (потокобезопасность из коробки)
 *
 * + Возможность использования EnumSet, EnumMap и т.д.
 * + Сериализация из коробки
 * + Поддержка switch
 */
