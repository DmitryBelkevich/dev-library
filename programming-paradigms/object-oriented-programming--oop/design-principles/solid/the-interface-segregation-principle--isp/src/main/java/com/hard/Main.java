package com.hard;

public class Main {
    public static void main(String[] args) {
        Device1 device1 = new Device1();
        device1.read();
        device1.write();

        Device2 device2 = new Device2();
        device2.read();
    }
}

/**
 * Bad example
 */

//interface IWorkable {
//    void read();
//    void write();
//}
//
//class Device1 implements IWorkable {
//    @Override
//    public void read() {
//        System.out.println("Device1 is reading");
//    }
//
//    @Override
//    public void write() {
//        System.out.println("Device1 is writing");
//    }
//}
//
//class Device2 implements IWorkable {
//    @Override
//    public void read() {
//        System.out.println("Device2 is reading");
//    }
//
//    @Override
//    public void write() {
//
//    }
//}

/**
 * Good example
 */

interface IReadable {
    void read();
}

interface IWritable {
    void write();
}

class Device1 implements IReadable, IWritable {
    @Override
    public void read() {
        System.out.println("Entity1 is reading");
    }

    @Override
    public void write() {
        System.out.println("Entity1 is writing");
    }
}

class Device2 implements IReadable {
    @Override
    public void read() {
        System.out.println("Entity2 is working");
    }
}
