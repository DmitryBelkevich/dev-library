package com.hard;

import java.lang.reflect.Field;

public class TreePrinter {
    private Tree tree;

    boolean showNulls = true;

    public TreePrinter() {
    }

    public TreePrinter(Tree tree) {
        this.tree = tree;
    }

    public Tree getTree() {
        return tree;
    }

    public void setTree(Tree tree) {
        this.tree = tree;
    }

    public boolean isShowNulls() {
        return showNulls;
    }

    public void setShowNulls(boolean showNulls) {
        this.showNulls = showNulls;
    }

    public void print() {
        BinarySearchTree.Node node = getRoot();

        System.out.println(node);
    }

    public BinarySearchTree.Node getRoot() {
        Class<?> treeClass = tree.getClass();

        Field field = null;
        try {
            field = treeClass.getDeclaredField("root");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

        BinarySearchTree.Node node = null;
        try {
            node = (BinarySearchTree.Node) field.get(tree);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return node;
    }
}
