package com.hard.ex1_objects_collection;

import org.junit.Assert;
import org.junit.Test;

public class ObjectsTest {
    @Test
    public void allObjectsFieldsShouldBeEquals() {
        Entity entity1 = new Entity("Hello World");
        Entity entity2 = new Entity("Hello World");

        boolean result = entity1.equals(entity2);

        Assert.assertEquals(true, result);
    }
}
