package com.hard._01_predicates;

import org.junit.Assert;
import org.junit.Test;

import java.util.Objects;
import java.util.function.Predicate;

public class PredicateTest {
    @Test
    public void test1() {
        Predicate<String> predicate = (str) -> str.length() > 0;

        Assert.assertTrue(predicate.test("Hello World"));
        Assert.assertFalse(predicate.negate().test("Hello World"));
    }

    @Test
    public void nonNull_test() {
        Predicate<Object> predicate = Objects::nonNull;

        Assert.assertTrue(predicate.test(new Object()));
        Assert.assertFalse(predicate.test(null));
    }

    @Test
    public void isNull_test() {
        Predicate<Object> predicate = Objects::isNull;

        Assert.assertTrue(predicate.test(null));
        Assert.assertFalse(predicate.test(new Object()));
    }

    @Test
    public void isEmpty_test() {
        Predicate<String> predicate = String::isEmpty;

        Assert.assertTrue(predicate.test(""));
        Assert.assertFalse(predicate.test("Hello World"));
    }
}

/**
 * Предикаты - это функции, принимающие один аргумент, и возвращающие значение типа boolean.
 * Интерфейс содержит различные методы по умолчанию, позволяющие строить сложные условия (and, or, negate)
 */
