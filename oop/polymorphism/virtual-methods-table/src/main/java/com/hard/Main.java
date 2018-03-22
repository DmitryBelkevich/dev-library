package com.hard;

public class Main {
    public static void main(String[] args) {
        Parent parent = new Child();
        parent.f();
    }
}

/**
 * virtual table
 */

class VTable {
    public String address;
    public String title;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}

/**
 * Classes
 */

class Parent {
    private VTable vTable;

    public Parent() {
        VTable vTable = new VTable();
        vTable.setAddress("ffa234");
        vTable.setTitle("Parent.f()");

        this.vTable = vTable;
    }

    public void f() {
        System.out.println(1);
    }
}

class Child extends Parent {
    private VTable vTable;

    public Child() {
        VTable vTable = new VTable();
        vTable.setAddress("fad1a2");
        vTable.setTitle("Child.f()");

        this.vTable = vTable;
    }

    @Override
    public void f() {
        System.out.println(2);
    }
}
