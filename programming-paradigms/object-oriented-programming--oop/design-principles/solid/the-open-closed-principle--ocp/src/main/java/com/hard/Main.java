package com.hard;

public class Main {
    public static void main(String[] args) {
        GraphicEditor graphicEditor = new GraphicEditor();

        graphicEditor.drawShape(new Circle());
    }
}

/**
 * Bad example
 */

class GraphicEditor {
    public void drawShape(Shape shape) {
        if (shape.type == 1)
            drawCircle((Circle) shape);
        else if (shape.type == 2)
            drawRectangle((Rectangle) shape);
    }

    public void drawCircle(Circle circle) {
        System.out.println("drawing circle");
    }

    public void drawRectangle(Rectangle rectangle) {
        System.out.println("drawing rectangle");
    }
}

class Shape {
    protected int type;
}

class Circle extends Shape {
    public Circle() {
        type = 1;
    }
}

class Rectangle extends Shape {
    public Rectangle() {
        type = 2;
    }
}
