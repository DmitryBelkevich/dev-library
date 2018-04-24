package com.hard;

import org.junit.Test;

public class TreePrinterTest {
    @Test
    public void should_not_print_empty_tree() {
        Tree<String> tree = new BinarySearchTree<>();

        TreePrinter treePrinter = new TreePrinter(new TreeWrapper(tree));
        treePrinter.print();
    }

    @Test
    public void should_print_tree() {
        Tree<String> tree = new BinarySearchTree<>();

        tree.add("4");
        tree.add("2");
        tree.add("1");
        tree.add("3");
        tree.add("6");
        tree.add("5");
        tree.add("7");

        TreePrinter treePrinter = new TreePrinter(new TreeWrapper(tree));
        treePrinter.print();
    }
}
