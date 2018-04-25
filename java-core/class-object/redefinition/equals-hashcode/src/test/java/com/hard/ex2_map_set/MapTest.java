package com.hard.ex2_map_set;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;

public class MapTest {
    /**
     * HashMap
     */

    @Test
    public void hashMapShouldReturnObject() {
        Map<Entity, Object> entities = new HashMap<>();

        Object object1 = new Object();

        entities.put(new Entity("Hello World"), object1);

        Object object2 = entities.get(new Entity("Hello World"));

        Assert.assertEquals(object1, object2);
    }

    /**
     * LinkedHashMap
     */

    @Test
    public void linkedHashMapShouldReturnObject() {
        Map<Entity, Object> entities = new LinkedHashMap<>();

        Object object1 = new Object();

        entities.put(new Entity("Hello World"), object1);

        Object object2 = entities.get(new Entity("Hello World"));

        Assert.assertEquals(object1, object2);
    }

    /**
     * Hashtable
     */

    @Test
    public void hashTableShouldReturnObject() {
        Map<Entity, Object> entities = new Hashtable<>();

        Object object1 = new Object();

        entities.put(new Entity("Hello World"), object1);

        Object object2 = entities.get(new Entity("Hello World"));

        Assert.assertEquals(object1, object2);
    }

    /**
     * TreeMap
     */
}
