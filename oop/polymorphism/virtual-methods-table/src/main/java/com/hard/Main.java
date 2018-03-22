package com.hard;

public class Main {
    public static void main(String[] args) {
        High c;

        c = new High();
        c.f(); // 1

        c = new Middle();
        c.f(); // 2

        c = new Low();
        c.f(); // 2
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
    public String title;
    public String address;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "VTable{" +
                "title='" + title + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}

abstract class VTableSupport {
    VTable vTable;
}

/**
 * Classes
 */

class High extends VTableSupport {
    public High() {
        VTable vTable = new VTable();

        vTable.setTitle(High.class + "." + "f()");
        vTable.setAddress(Memory.cell1);

        this.vTable = vTable;
    }

    public void f() {
        System.out.println(this.vTable);
    }
}

class Middle extends High {
    public Middle() {
        VTable vTable = new VTable();

        vTable.setTitle(Middle.class + "." + "f()");
        vTable.setAddress(Memory.cell2);

        this.vTable = vTable;
    }

    @Override
    public void f() {
        super.f();
    }
}

class Low extends Middle {
    public Low() {
        VTable vTable = new VTable();

        vTable.setTitle(Middle.class + "." + "f()");
        vTable.setAddress(Memory.cell2);

        this.vTable = vTable;
    }
}
