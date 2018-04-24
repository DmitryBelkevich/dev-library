package com.hard;

public class TreePrinter<T> {
    private TreeWrapper<T> tree;

    boolean showNulls = true;

    public TreePrinter() {
    }

    public TreePrinter(TreeWrapper<T> tree) {
        this.tree = tree;
    }

    public TreeWrapper<T> getTree() {
        return tree;
    }

    public void setTree(TreeWrapper<T> tree) {
        this.tree = tree;
    }

    public boolean isShowNulls() {
        return showNulls;
    }

    public void setShowNulls(boolean showNulls) {
        this.showNulls = showNulls;
    }

    public void print() {
        BinarySearchTree.Node node = tree.getRoot();

        System.out.println(node);
    }
}
