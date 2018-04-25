package com.hard.ex2_map_set;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;

public class SetTest {
    /**
     * HashSet
     */

    @Test
    public void hashSetShouldContainsObject() {
        Collection<Entity> entities = new HashSet<>();
        Entity entity = new Entity("Hello World");
        entities.add(entity);

        boolean result = entities.contains(new Entity("Hello World"));

        Assert.assertEquals(true, result);
    }

    @Test
    public void shouldRemoveObjectFromHashSet() {
        Collection<Entity> entities = new HashSet<>();
        Entity entity = new Entity("Hello World");
        entities.add(entity);

        boolean result = entities.remove(new Entity("Hello World"));

        Assert.assertEquals(true, result);
    }

    /**
     * LinkedHashSet
     */

    @Test
    public void linkedHashSetShouldContainsObject() {
        Collection<Entity> entities = new LinkedHashSet<>();
        Entity entity = new Entity("Hello World");
        entities.add(entity);

        boolean result = entities.contains(new Entity("Hello World"));

        Assert.assertEquals(true, result);
    }

    /**
     * TreeSet
     */
}
