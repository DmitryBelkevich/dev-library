package com.hard.ex1_objects_collection;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;

public class CollectionTest {
    /**
     * ArrayList
     */

    @Test
    public void arrayListShouldContainsObject() {
        Collection<Entity> entities = new ArrayList<>();
        Entity entity = new Entity("Hello World");
        entities.add(entity);

        boolean result = entities.contains(new Entity("Hello World"));

        Assert.assertEquals(true, result);
    }

    @Test
    public void shouldRemoveObjectFromArrayList() {
        Collection<Entity> entities = new ArrayList<>();
        Entity entity = new Entity("Hello World");
        entities.add(entity);

        boolean result = entities.remove(new Entity("Hello World"));

        Assert.assertEquals(true, result);
    }

    /**
     * LinkedList
     */

    @Test
    public void linkedListShouldContainsObject() {
        Collection<Entity> entities = new LinkedList<>();
        Entity entity = new Entity("Hello World");
        entities.add(entity);

        boolean result = entities.contains(new Entity("Hello World"));

        Assert.assertEquals(true, result);
    }
}
