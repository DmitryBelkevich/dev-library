package com.hard;

import java.lang.reflect.Field;

public class TreeWrapper<T> implements Tree<T> {
    private Tree<T> tree;

    public TreeWrapper(Tree<T> tree) {
        this.tree = tree;
    }

    @Override
    public void add(T data) {
        tree.add(data);
    }

    @Override
    public void remove(T data) {
        tree.remove(data);
    }

    @Override
    public int size() {
        return tree.size();
    }

    @Override
    public int depth() {
        return tree.depth();
    }

    @Override
    public boolean isBalanced() {
        return tree.isBalanced();
    }

    @Override
    public void rotateToLeft() {
        tree.rotateToLeft();
    }

    @Override
    public void rotateToRight() {
        tree.rotateToRight();
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

    public T getNodeData() {
        Class<?> treeClass = tree.getClass();

        Field field = null;
        try {
            field = treeClass.getDeclaredField("root");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

        Object node = null;
        try {
            node = field.get(tree);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        Class<?> c = node.getClass();

        for (Field f : c.getDeclaredFields())
            System.out.println(f);

        Field f = null;
        try {
            f = c.getDeclaredField("data");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

//        f.get();

        T data = null;

        return data;
    }
}
