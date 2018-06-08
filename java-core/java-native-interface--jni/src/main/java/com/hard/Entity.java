package com.hard;

public class Entity {
    public native void print1();
    public static native void print2();

    static {
        System.loadLibrary("library");
    }
}
