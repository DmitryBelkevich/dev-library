package com.hard;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        High c;

        c = new High();
        c.f(); // 1

        c = new Middle();
        c.f(); // 2

        Low d = new Low();
        d.f(); // 2
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
        System.out.println(this.vTable);
    }
}

class Middle extends High {
    private Map<String, String> vTable = new HashMap<>();

    public Middle() {
        vTable.put(Middle.class.getName() + "." + "f()", Memory.cell2);
    }

    @Override
    public void f() {
        System.out.println(this.vTable);
    }
}

class Low extends Middle {
    private Map<String, String> vTable = new HashMap<>();

    public Low() {
        vTable.put(Middle.class.getName() + "." + "f()", Memory.cell2);
    }
}
