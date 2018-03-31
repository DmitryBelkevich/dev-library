package com.hard;

public class Main {
    public static void main(String[] args) {
        Shape shape1 = new Circle(new RedCircle());
        Shape shape2 = new Circle(new GreenCircle());

        shape1.draw();
        shape2.draw();
    }
}

/**
 * Implementator
 */

interface Graphics {
    void draw();    // operationImpl
}

/**
 * Concrete Implementator
 */

class RedCircle implements Graphics {
    @Override
    public void draw() {
        System.out.println("RedCircle");
    }
}

class GreenCircle implements Graphics {
    @Override
    public void draw() {
        System.out.println("GreenCircle");
    }
}

/**
 * Abstraction
 */

abstract class Shape {
    protected Graphics graphics;

    public Shape(Graphics graphics) {
        this.graphics = graphics;
    }

    public abstract void draw();    //operation
}

/**
 * Refined Abstraction
 */

class Circle extends Shape {
    public Circle(Graphics graphics) {
        super(graphics);
    }

    @Override
    public void draw() {
        System.out.println("Circle");
        graphics.draw();
    }
}

class Rectangle extends Shape {
    public Rectangle(Graphics graphics) {
        super(graphics);
    }

    @Override
    public void draw() {
        System.out.println("Rectangle");
        graphics.draw();
    }
}
