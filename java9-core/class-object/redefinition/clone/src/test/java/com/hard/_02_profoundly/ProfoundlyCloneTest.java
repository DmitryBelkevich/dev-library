package com.hard._02_profoundly;

import org.junit.Assert;
import org.junit.Test;

public class ProfoundlyCloneTest {
    @Test
    public void shouldCloneObjectAsProfoundly() {
        Entity entity1 = new Entity(1, "Hello World");
        Entity entity2 = null;

        try {
            entity2 = (Entity) entity1.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        Assert.assertNotSame(entity1, entity2);

        Assert.assertEquals(entity1.getId(), entity2.getId());
        Assert.assertNotSame(entity1.getStr(), entity2.getStr());
    }
}
