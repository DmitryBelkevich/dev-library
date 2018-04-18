package com.hard._01_separate_chaining;

import org.junit.Assert;
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
    public void should_return_null_if_not_contains() {
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

        Assert.assertNull(map.get("A"));
    }

    @Test
    public void should_return_node_by_key() {
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

        Assert.assertEquals("aaa", map.get("a"));
        Assert.assertEquals("bbb", map.get("b"));
        Assert.assertEquals("ccc", map.get("c"));
        Assert.assertEquals("ddd", map.get("d"));
        Assert.assertEquals("eee", map.get("e"));
        Assert.assertEquals("fff", map.get("f"));
        Assert.assertEquals("ggg", map.get("g"));
        Assert.assertEquals("hhh", map.get("h"));
        Assert.assertEquals("iii", map.get("i"));
        Assert.assertEquals("jjj", map.get("j"));
        Assert.assertEquals("kkk", map.get("k"));
        Assert.assertEquals("lll", map.get("l"));
        Assert.assertEquals("mmm", map.get("m"));
        Assert.assertEquals("nnn", map.get("n"));
        Assert.assertEquals("ooo", map.get("o"));
        Assert.assertEquals("ppp", map.get("p"));
        Assert.assertEquals("qqq", map.get("q"));
        Assert.assertEquals("rrr", map.get("r"));
        Assert.assertEquals("sss", map.get("s"));
        Assert.assertEquals("ttt", map.get("t"));
        Assert.assertEquals("uuu", map.get("u"));
        Assert.assertEquals("vvv", map.get("v"));
        Assert.assertEquals("www", map.get("w"));
        Assert.assertEquals("xxx", map.get("x"));
        Assert.assertEquals("yyy", map.get("y"));
        Assert.assertEquals("zzz", map.get("z"));
    }

    /**
     * add
     */

    @Test
    public void should_add_nodes_without_collisions() {
        Map<String, String> map = new HashTable<>();

        map.add("a", "aaa");
        map.add("b", "bbb");
        map.add("c", "ccc");
        map.add("d", "ddd");
        map.add("e", "eee");
        map.add("f", "fff");
        map.add("g", "ggg");
        map.add("h", "hhh");

        Assert.assertEquals(8, map.size());
    }

    @Test
    public void should_add_nodes_with_collisions() {
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
}
