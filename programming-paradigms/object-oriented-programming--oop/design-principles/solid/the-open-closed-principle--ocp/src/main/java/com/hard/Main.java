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

abstract class Shape {

}

class Circle extends Shape {

}

class Rectangle extends Shape {

}

class GraphicEditor {
    public void drawShape(Shape shape) {
        if (shape instanceof Circle)
            drawCircle((Circle) shape);
        else if (shape instanceof Rectangle)
            drawRectangle((Rectangle) shape);
    }

    public void drawCircle(Circle circle) {
        System.out.println("drawing circle");
    }

    public void drawRectangle(Rectangle rectangle) {
        System.out.println("drawing rectangle");
    }
}

/**
 * Good example
 */
