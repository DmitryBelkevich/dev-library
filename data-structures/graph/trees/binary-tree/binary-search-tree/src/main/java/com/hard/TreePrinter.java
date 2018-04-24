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
}
