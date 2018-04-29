package com.hard._02_functions;

import org.junit.Assert;
import org.junit.Test;

import java.util.function.Function;

public class FunctionTest {
    @Test
    public void test1() {
        Function<String, Integer> function = Integer::valueOf;
        Function<String, String> function2 = function.andThen(String::valueOf);

        Assert.assertEquals("1", function2.apply("1"));
    }
}

/**
 * Функции принимают один аргумент и возвращают некоторый результат.
 * Методы по умолчанию могут использоваться для построения цепочек вызовов (compose, andThen)
 */
