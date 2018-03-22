package com.hard;

public class Main {
    public static void main(String[] args) {
        Parent parent = new Child2();
        parent.f();
    }
}

/**
 * Constants
 */

interface Memory {
    String cell1 = "ffa234";
    String cell2 = "fad1a2";
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
        vTable.setAddress(Memory.cell1);
        vTable.setTitle("Parent.f()");

        this.vTable = vTable;
    }

    public void f() {
        System.out.println(vTable.address);
    }
}

class Child extends Parent {
    private VTable vTable;

    public Child() {
        VTable vTable = new VTable();
        vTable.setAddress(Memory.cell2);
        vTable.setTitle("Child.f()");

        this.vTable = vTable;
    }

    @Override
    public void f() {
        System.out.println(vTable.address);
    }
}

class Child2 extends Parent {
    private VTable vTable;

    public Child2() {
        VTable vTable = new VTable();
        vTable.setAddress(Memory.cell2);
        vTable.setTitle("Child.f()");

        this.vTable = vTable;
    }
}
