package com.hard;

import java.util.ArrayList;
import java.util.Collection;

public class TreePrinter<T> {
    private TreeWrapper<T> tree;

    boolean showNulls = true;
    String spaceL = "+";
    String spaceR = "-";

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
        BinarySearchTree.Node root = tree.getRoot();

        if (root == null)
            return;

        int depthTree = 5;//tree.depth();   // 5

        int level = 0;

        level = 1;  // w: = 64, h: 1 + 32 - 1 = 32
        level = 2;  // w: = 32, h: 1 + 16 - 1 = 16
        level = 3;  // w: = 16, h: 1 + 8 - 1 = 8
        level = 4;  // w: = 8, h: 1 + 4 - 1 = 4
        level = 5;  // w: = 4, h: 1 + 2 - 1 = 2

        Collection<T> collection;

        /**
         *  Level 1
         */

        collection = new ArrayList<>();
        collection.add((T) "1");

        drawRow(collection, 64, 32);

        /**
         *  Level 2
         */

        collection = new ArrayList<>();
        collection.add((T) "1");
        collection.add((T) "2");

        drawRow(collection, 32, 16);

        /**
         *  Level 3
         */

        collection = new ArrayList<>();
        collection.add((T) "1");
        collection.add((T) "2");
        collection.add((T) "3");
        collection.add((T) "4");

        drawRow(collection, 16, 8);

        /**
         *  Level 4
         */

        collection = new ArrayList<>();
        collection.add((T) "1");
        collection.add((T) "2");
        collection.add((T) "3");
        collection.add((T) "4");
//        collection.add((T) "5");
        collection.add(null);
        collection.add((T) "6");
//        collection.add((T) "7");
//        collection.add((T) "8");

        drawRow(collection, 8, 4);

        /**
         *  Level 5
         */

        collection = new ArrayList<>();
        collection.add((T) "1");
        collection.add((T) "2");
        collection.add((T) "3");
        collection.add((T) "4");
        collection.add((T) "5");
        collection.add((T) "6");
        collection.add((T) "7");
        collection.add((T) "8");
//        collection.add((T) "1");
//        collection.add((T) "2");
//        collection.add((T) "3");
//        collection.add((T) "4");
//        collection.add((T) "5");
//        collection.add((T) "6");
//        collection.add((T) "7");
//        collection.add((T) "8");

        drawRow(collection, 4, 2);
    }

    public void drawRow(Collection<T> collection, int w, int h) {
        StringBuilder stringBuilder = new StringBuilder();

        for (T t : collection) {
            drawNode(stringBuilder, t, w);
        }

        System.out.println(stringBuilder);
    }

    public void drawNode(StringBuilder stringBuilder, T t, int off) {
        int lengthStr = 0;

        if (t != null)
            lengthStr = t.toString().length();

        if (t == null)
            lengthStr = 4;

        for (int i = 0; i < off / 2; i++) {
            stringBuilder.append(spaceL);
        }

        stringBuilder.append(t);

        for (int i = 0; i < off / 2 - lengthStr; i++) {
            stringBuilder.append(spaceR);
        }
    }

    public void drawArc(StringBuilder stringBuilder, int w, int h) {

    }
}
