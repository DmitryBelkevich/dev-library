package com.hard._03_suppliers;

import org.junit.Assert;
import org.junit.Test;

import java.util.function.Supplier;

public class SupplierTest {
    @Test
    public void test() {
        Supplier<Object> supplier = Object::new;

        Assert.assertNotNull(supplier.get());
    }
}

/**
 * Поставщики (suppliers) предоставляют результат заданного типа.
 * В отличии от функций, поставщики не принимают аргументов
 */
