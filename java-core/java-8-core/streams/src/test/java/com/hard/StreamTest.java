package com.hard;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collection;

public class StreamTest {
    @Test
    public void test1() {
        Collection<String> collection = Arrays.asList("aa1", "cc2", "cc1", "aa2", "bb1");

        collection
                .stream()
                .filter(s -> s.startsWith("a"))
                .map(String::toUpperCase)
                .sorted()
                .forEach(System.out::println);
    }
}
