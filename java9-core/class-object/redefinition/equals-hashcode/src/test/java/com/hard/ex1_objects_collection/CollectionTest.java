package com.hard.ex1_objects_collection;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;

public class CollectionTest {
    @Test
    public void collectionShouldContainsObject() {
        Collection<Entity> entities = new ArrayList<>();
        Entity entity = new Entity("Hello World");
        entities.add(entity);

        boolean result = entities.contains(new Entity("Hello World"));

        Assert.assertEquals(true, result);
    }

    @Test
    public void shouldRemoveObjectFromCollection() {
        Collection<Entity> entities = new ArrayList<>();
        Entity entity = new Entity("Hello World");
        entities.add(entity);

        boolean result = entities.remove(new Entity("Hello World"));

        Assert.assertEquals(true, result);
    }
}
