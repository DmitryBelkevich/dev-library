package com.hard.example1;

public class Main {
    public static void main(String[] args) {
        GraphicEditor graphicEditor = new GraphicEditor();

        graphicEditor.drawShape(new Circle());
    }
}

/**
 * Bad example 1
 */

//interface Shape {
//
//}
//
//class Circle implements Shape {
//
//}
//
//class Rectangle implements Shape {
//
//}
//
//class GraphicEditor {
//    public void drawShape(Shape shape) {
//        if (shape instanceof Circle)
//            drawCircle((Circle) shape);
//        else if (shape instanceof Rectangle)
//            drawRectangle((Rectangle) shape);
//    }
//
//    public void drawCircle(Circle circle) {
//        System.out.println("drawing circle");
//    }
//
//    public void drawRectangle(Rectangle rectangle) {
//        System.out.println("drawing rectangle");
//    }
//}

/**
 * Bad example 2
 */

//abstract class Shape {
//    protected int type;
//}
//
//class Circle extends Shape {
//    public Circle() {
//        super.type = 1;
//    }
//}
//
//class Rectangle extends Shape {
//    public Rectangle() {
//        super.type = 2;
//    }
//}
//
//class GraphicEditor {
//    public void drawShape(Shape shape) {
//        if (shape.type == 1)
//            drawCircle((Circle) shape);
//        else if (shape.type == 2)
//            drawRectangle((Rectangle) shape);
//    }
//
//    public void drawCircle(Circle circle) {
//        System.out.println("drawing circle");
//    }
//
//    public void drawRectangle(Rectangle rectangle) {
//        System.out.println("drawing rectangle");
//    }
//}

/**
 * Good example
 */

interface Shape {
    void draw();
}

class Circle implements Shape {
    @Override
    public void draw() {
        System.out.println("drawing circle");
    }
}

class Rectangle implements Shape {
    @Override
    public void draw() {
        System.out.println("drawing rectangle");
    }
}

class GraphicEditor {
    public void drawShape(Shape shape) {
        shape.draw();
    }
}
