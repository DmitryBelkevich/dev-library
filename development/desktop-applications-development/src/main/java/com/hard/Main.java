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
        Shape circle = new Circle();

        circle.setX(0);
        circle.setY(0);
        circle.setW(100);
        circle.setH(100);

        Shape rectangle = new Rectangle();

        rectangle.setX(200);
        rectangle.setY(200);
        rectangle.setW(100);
        rectangle.setH(100);

        Collection<Shape> shapes = new ArrayList<>();

        shapes.add(circle);
        shapes.add(rectangle);

        // controller
        Editor editor = new Editor(rectangle);
        editor.run();
    }
}
