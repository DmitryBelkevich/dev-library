package com.hard;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

public class BinaryTreeTest {
    public static class Add {
        @Test
        public void should_return_empty_tree_size() {
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
        public void should_add_nodes_and_return_tree_size() {
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

    public static class Remove {
        /**
         * 0 level (empty tree)
         */

        @Test
        public void should_not_remove_nodes_from_empty_tree() {
            Tree<String> tree = new BinaryTree<>();

            tree.remove("1");

            Assert.assertEquals(0, tree.size());
        }

        /**
         * 1 level
         */

        @Test
        public void should_remove_root_node_and_return_tree_size_level_1() {
            Tree<String> tree = new BinaryTree<>();

            tree.add("1");

            tree.remove("1");

            Assert.assertEquals(0, tree.size());
        }

        /**
         * 2 level
         */

        @Test
        public void should_remove_nodes_and_return_tree_size_level_2_left() {
            Tree<String> tree = new BinaryTree<>();

            tree.add("3");
            tree.add("2");
            tree.add("1");
            tree.add("5");
            tree.add("4");
            tree.add("3");
            tree.add("6");
            tree.add("7");

            tree.remove("2");

            Assert.assertEquals(8 - 2, tree.size());
        }

        @Test
        public void should_remove_nodes_and_return_tree_size_level_2_right() {
            Tree<String> tree = new BinaryTree<>();

            tree.add("3");
            tree.add("2");
            tree.add("1");
            tree.add("5");
            tree.add("4");
            tree.add("3");
            tree.add("6");
            tree.add("7");

            tree.remove("5");

            Assert.assertEquals(8 - 5, tree.size());
        }

        /**
         * 3 level
         */

        @Test
        public void should_remove_nodes_and_return_tree_size_level_3_left() {
            Tree<String> tree = new BinaryTree<>();

            tree.add("3");
            tree.add("2");
            tree.add("1");
            tree.add("5");
            tree.add("4");
            tree.add("3");
            tree.add("6");
            tree.add("7");

            tree.remove("1");

            Assert.assertEquals(8 - 1, tree.size());
        }

        @Test
        public void should_remove_nodes_and_return_tree_size_level_3_right() {
            Tree<String> tree = new BinaryTree<>();

            tree.add("3");
            tree.add("2");
            tree.add("1");
            tree.add("5");
            tree.add("4");
            tree.add("3");
            tree.add("6");
            tree.add("7");

            tree.remove("4");

            Assert.assertEquals(8 - 2, tree.size());
        }

        /**
         * 4 level
         */

        @Test
        public void should_remove_nodes_and_return_tree_size_level_4_right() {
            Tree<String> tree = new BinaryTree<>();

            tree.add("3");
            tree.add("2");
            tree.add("1");
            tree.add("5");
            tree.add("4");
            tree.add("3");
            tree.add("6");
            tree.add("7");

            tree.remove("7");

            Assert.assertEquals(8 - 1, tree.size());
        }
    }
}
