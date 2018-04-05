package com.hard.memory;

public class Main {
    public static void main(String[] args) {
        long totalMemory = Runtime.getRuntime().totalMemory();
        long freeMemory = Runtime.getRuntime().freeMemory();

        System.out.println(totalMemory / 8 / 1024 / 1024 + " Mb");
        System.out.println(freeMemory / 8 / 1024 / 1024 + " Mb");
    }
}
