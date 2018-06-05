package com.hard.tools;

import com.hard.models.Circle;
import com.hard.models.Rectangle;
import com.hard.models.Shape;

import java.awt.event.MouseEvent;
import java.util.Collection;

public class ScaleTool extends Tool {
    public ScaleTool(Collection<Shape> shapes) {
        super(shapes);
    }

    @Override
    public void update() {

    }

    @Override
    public void draw() {

    }

    /**
     * Handle input
     */

    @Override
    public void mousePressed(MouseEvent e) {
        for (Shape shape : shapes) {
            if (shape.intersected(e.getX(), e.getY())) {
                shape.setSelected(true);
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        for (Shape shape : shapes) {
            shape.setSelected(false);
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        for (Shape shape : shapes) {
            if (!shape.isSelected()) {
                if (shape.intersected(e.getX(), e.getY())) {
                    shape.setOver(true);
                } else {
                    shape.setOver(false);
                }
            }
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        for (Shape shape : shapes) {
            if (shape.isSelected()) {
                if (shape instanceof Rectangle) {
                    Rectangle rectangle = (Rectangle) shape;

                    rectangle.setW(rectangle.getW() + 1);
                    rectangle.setH(rectangle.getH() + 1);
                }

                if (shape instanceof Circle) {
                    Circle circle = (Circle) shape;

                    circle.setR(circle.getR() + 1);
                }
            }
        }
    }
}
