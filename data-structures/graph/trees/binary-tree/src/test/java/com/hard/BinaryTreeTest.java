package com.hard;

import org.junit.Assert;
import org.junit.Test;

public class BinaryTreeTest {
    /**
     * size
     */

    @Test
    public void should_return_0_from_empty_tree() {
        Tree<String> tree = new BinaryTree<>();

        Assert.assertEquals(0, tree.size());
    }

    @Test
    public void should_add_one_node_and_return_tree_size() {
        Tree<String> tree = new BinaryTree<>();

        tree.add("1");

        Assert.assertEquals(1, tree.size());
    }

    @Test
    public void should_return_tree_size() {
        Tree<String> tree = new BinaryTree<>();

        tree.add("3");
        tree.add("2");
        tree.add("1");
        tree.add("5");
        tree.add("4");
        tree.add("3");
        tree.add("6");
        tree.add("7");

        Assert.assertEquals(8, tree.size());
    }
}
