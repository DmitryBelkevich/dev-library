package com.hard;

public class Main {
    public static void main(String[] args) {
        Shape shape1 = new Circle(1, 1, 1, new RedCircle());
        Shape shape2 = new Circle(1, 1, 1, new GreenCircle());

        shape1.draw();
        shape2.draw();
    }
}

/**
 * Implementator
 */

interface Graphics {
    void draw(int r, int x, int y);    // operationImpl
}

/**
 * Concrete Implementator
 */

class RedCircle implements Graphics {
    @Override
    public void draw(int r, int x, int y) {
        System.out.println(
                "RedCircle:"
                        + "r=" + r
                        + ",x=" + x
                        + ",y=" + y
        );
    }
}

class GreenCircle implements Graphics {
    @Override
    public void draw(int r, int x, int y) {
        System.out.println(
                "GreenCircle:"
                        + "r=" + r
                        + ",x=" + x
                        + ",y=" + y
        );
    }
}

/**
 * Abstraction
 */

abstract class Shape {
    protected Graphics graphics;

    protected Shape(Graphics graphics) {
        this.graphics = graphics;
    }

    public abstract void draw();    //operation
}

/**
 * Refined Abstraction
 */

class Circle extends Shape {
    private int r;
    private int x;
    private int y;

    public Circle(int r, int x, int y, Graphics graphics) {
        super(graphics);
        this.r = r;
        this.x = x;
        this.y = y;
    }

    @Override
    public void draw() {
        graphics.draw(r, x, y);
    }
}
