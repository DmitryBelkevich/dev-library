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

interface Shape {

}

class Circle implements Shape {

}

class Rectangle implements Shape {

}

/**
 * Good example
 */
