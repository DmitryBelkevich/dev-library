package com.hard._01_separate_chaining;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

public class HashTableTest {
    /**
     * size
     */

    @Test
    public void should_be_size_equals_number_of_nodes_1() {
        Map<String, String> map = new HashTable<>();

        Assert.assertEquals(0, map.size());
    }

    @Test
    public void should_be_size_equals_number_of_nodes_2() {
        Map<String, String> map = new HashTable<>();

        map.add("a", "aaa");
        map.add("b", "bbb");
        map.add("c", "ccc");
        map.add("d", "ddd");
        map.add("e", "eee");
        map.add("f", "fff");
        map.add("g", "ggg");
        map.add("h", "hhh");
        map.add("i", "iii");
        map.add("j", "jjj");
        map.add("k", "kkk");
        map.add("l", "lll");
        map.add("m", "mmm");
        map.add("n", "nnn");
        map.add("o", "ooo");
        map.add("p", "ppp");
        map.add("q", "qqq");
        map.add("r", "rrr");
        map.add("s", "sss");
        map.add("t", "ttt");
        map.add("u", "uuu");
        map.add("v", "vvv");
        map.add("w", "www");
        map.add("x", "xxx");
        map.add("y", "yyy");
        map.add("z", "zzz");

        Assert.assertEquals(26, map.size());
    }

    /**
     * get
     */

    @Test
    @Ignore
    public void should_return_node_by_key() {
        Map<String, String> map = new HashTable<>();

        Assert.assertEquals(16, map.get("a"));
    }

    /**
     * add
     */

    @Test
    @Ignore
    public void should_add_nodes() {
        Map<String, String> map = new HashTable<>();

        Assert.assertEquals(0, map.size());
    }
}
