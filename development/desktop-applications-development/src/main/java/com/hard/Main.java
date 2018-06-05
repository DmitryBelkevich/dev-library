package com.hard;

import com.hard.controllers.Editor;
import com.hard.models.Circle;
import com.hard.models.Shape;

public class Main {
    public static void main(String[] args) {
        // load from file
        Shape circle = new Circle();

        circle.setX(0);
        circle.setY(0);
        circle.setW(100);
        circle.setH(100);

        // controller
        Editor editor = new Editor(circle);
        editor.run();
    }
}
