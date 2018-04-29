package com.hard._05_optionals;

import org.junit.Assert;
import org.junit.Test;

import java.util.Optional;

public class OptionalTest {
    @Test
    public void isPresent_test() {
        Optional<String> optional = Optional.of("Hello World");

        Assert.assertTrue(optional.isPresent());
    }

    @Test
    public void get_test() {
        Optional<String> optional = Optional.of("Hello World");

        Assert.assertEquals("Hello World", optional.get());
    }

    @Test
    public void orElse_test() {
        Optional<String> optional = Optional.empty();

        Assert.assertEquals("Hello World", optional.orElse("Hello World"));
    }

    @Test
    public void ifPresent_test() {
        Optional<String> optional = Optional.of("Hello World");

        optional.ifPresent((str) -> System.out.println(str));
    }
}
