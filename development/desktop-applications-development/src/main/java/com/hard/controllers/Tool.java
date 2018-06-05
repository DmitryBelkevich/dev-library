package com.hard.controllers;

import com.hard.models.Shape;

import java.awt.event.MouseEvent;
import java.util.Collection;

public class Tool {
    private Collection<Shape> shapes;

    public Tool(Collection<Shape> shapes) {
        this.shapes = shapes;
    }

    public void update() {

    }

    public void draw() {

    }

    /**
     * Handle input
     */

    public void mousePressed(MouseEvent e) {
        for (Shape shape : shapes) {
            if (shape.intersected(e.getX(), e.getY())) {
                shape.setSelected(true);
            }
        }
    }

    public void mouseReleased(MouseEvent e) {
        for (Shape shape : shapes) {
            shape.setSelected(false);
        }
    }

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

    public void mouseDragged(MouseEvent e) {
        for (Shape shape : shapes) {
            if (shape.isSelected()) {
                shape.setX(e.getX() /*- shape.getW() / 2*/);
                shape.setY(e.getY() /*- shape.getH() / 2*/);
            }
        }
    }
}
