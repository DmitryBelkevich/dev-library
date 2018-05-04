package com.hard;

public class Main {
    public static void main(String[] args) {
        Square square = new Square();

        square.setWidth(2);

        System.out.println(square.square());
    }
}

/**
 * Bad example
 */

//class Rectangle {
//    private int width;
//    private int height;
//
//    public int getWidth() {
//        return width;
//    }
//
//    public void setWidth(int width) {
//        this.width = width;
//    }
//
//    public int getHeight() {
//        return height;
//    }
//
//    public void setHeight(int height) {
//        this.height = height;
//    }
//
//    public int square() {
//        return width * height;
//    }
//}
//
//class Square extends Rectangle {
//    @Override
//    public void setWidth(int width) {
//        super.setWidth(width);
//        super.setHeight(width);
//    }
//
//    @Override
//    public void setHeight(int height) {
//        super.setHeight(height);
//        super.setWidth(height);
//    }
//}

/**
 * Good example
 */

interface Shape {
    int square();
}

class Rectangle implements Shape {
    private int width;
    private int height;

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public int square() {
        return width * height;
    }
}

class Square implements Shape {
    private int width;

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    @Override
    public int square() {
        return width * width;
    }
}
