package com.hard;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        High obj;

        obj = new High();
        obj.f(); // 1

        obj = new Middle();
        obj.f(); // 2

        obj = new Low();
        obj.f(); // 2
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
 * Classes
 */

class High {
    private Map<String, String> vTable = new HashMap<>();

    public High() {
        vTable.put(High.class.getName() + "." + "f()", Memory.cell1);
    }

    public void f() {
        System.out.println(this.vTable.get(High.class.getName() + "." + "f()"));
    }

    public String getVTable() {
        return vTable.toString();
    }
}

class Middle extends High {
    private Map<String, String> vTable = new HashMap<>();

    public Middle() {
        vTable.put(Middle.class.getName() + "." + "f()", Memory.cell2);
    }

    @Override
    public void f() {
        System.out.println(this.vTable.get(Middle.class.getName() + "." + "f()"));
    }

    public String getVTable() {
        return vTable.toString();
    }
}

class Low extends Middle {
    private Map<String, String> vTable = new HashMap<>();

    public Low() {
        vTable.put(Middle.class.getName() + "." + "f()", Memory.cell2);
    }

    public String getVTable() {
        return vTable.toString();
    }
}
