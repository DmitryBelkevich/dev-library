package com.hard._02_testing_void_methods;

import org.junit.Assert;
import org.junit.Test;

public class EntityTest {
    @Test
    public void print_test() {
        Entity entity = new Entity();

        Assert.assertEquals(entity.i, 10);
        Assert.assertEquals(entity.j, 10);

        entity.opration();

        Assert.assertEquals(entity.i, 20);
        Assert.assertEquals(entity.j, 100);
    }
}
