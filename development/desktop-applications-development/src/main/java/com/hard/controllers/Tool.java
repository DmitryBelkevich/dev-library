package com.hard.controllers;

import com.hard.models.Shape;

import java.awt.event.MouseEvent;

public class Tool {
    private Shape shape;

    public Tool(Shape shape) {
        this.shape = shape;
    }

    public void update() {

    }

    /**
     * Handle input
     */

    public void mousePressed(MouseEvent e) {
        shape.setSelected(true);
    }

    public void mouseReleased(MouseEvent e) {
        shape.setSelected(false);
    }

    public void mouseDragged(MouseEvent e) {
        if (shape.isSelected()) {
            shape.setX(e.getX() - shape.getW() / 2);
            shape.setY(e.getY() - shape.getH() / 2);
        }
    }
}
