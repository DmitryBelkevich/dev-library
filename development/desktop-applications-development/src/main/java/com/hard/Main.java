package com.hard;

import com.hard.controllers.Editor;
import com.hard.models.Circle;
import com.hard.models.Rectangle;
import com.hard.models.Shape;

import java.util.ArrayList;
import java.util.Collection;

public class Main {
    public static void main(String[] args) {
        // load from file
        Rectangle rectangle = new Rectangle();

        rectangle.setX(100);
        rectangle.setY(100);
        rectangle.setW(100);
        rectangle.setH(100);

        Circle circle = new Circle();

        circle.setX(50);
        circle.setY(50);
        circle.setR(50);

        Collection<Shape> shapes = new ArrayList<>();

        shapes.add(rectangle);
        shapes.add(circle);

        // controller
        Editor editor = new Editor(shapes);
        editor.run();
    }
}
