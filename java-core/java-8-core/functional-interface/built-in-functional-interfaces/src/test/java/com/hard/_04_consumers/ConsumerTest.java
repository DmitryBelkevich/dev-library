package com.hard._04_consumers;

import org.junit.Test;

import java.util.function.Consumer;

public class ConsumerTest {
    @Test
    public void test() {
        Consumer<String> consumer = (str) -> System.out.println(str);

        consumer.accept("Hello World");
    }
}

/**
 * Потребители (consumers) представляют собой операции, которые производятся над одним входным аргументом
 */
