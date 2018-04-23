package com.hard._01_shallow;

import org.junit.Assert;
import org.junit.Test;

public class ShallowCloneTest {
    @Test
    public void shouldCloneObjectAsShallow() {
        Entity entity1 = new Entity(1, "Hello World");
        Entity entity2 = null;

        try {
            entity2 = (Entity) entity1.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        Assert.assertNotSame(entity1, entity2);

        Assert.assertEquals(entity1.getId(), entity2.getId());
        Assert.assertSame(entity1.getStr(), entity2.getStr());
    }
}
