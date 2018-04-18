package com.hard._01_separate_chaining;

import org.junit.Assert;
import org.junit.Test;

public class HashTableTest {
    /**
     * size
     */

    @Test
    public void should_be_size_equals_number_of_nodes() {
        Map<String, String> map = new HashTable<>();

        Assert.assertEquals(16, map.size());
    }
}
