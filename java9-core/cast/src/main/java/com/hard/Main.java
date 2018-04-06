package com.hard;

public class Main {
    public static void main(String[] args) {
        byte b1 = 0;
        short s1 = b1;
        int i1 = s1;
        long l1 = i1;
        float f1 = l1;
        double d1 = f1;

        float f2 = (float) d1;
        long l2 = (long) f2;
        int i2 = (int) l2;
        short s2 = (short) i2;
        byte b2 = (byte) s2;

        Object object = new String();
    }
}

/**
 * автоматическое (не явное) приведение
 *
 * Для примитивов:
 * при расширении типа:
 * byte -> short -> int -> long -> float -> double
 *
 * Для объектов:
 * От родительского класса к классу-наследнику:
 * Object -> Number -> Integer
 *
 * не автоматическое (явное) приведение
 *
 * Для примитивов:
 * при сужении типа:
 * double -> float -> long -> int -> short -> byte
 */
