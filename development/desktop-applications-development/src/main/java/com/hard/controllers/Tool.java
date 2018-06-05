package com.hard.controllers;

import com.hard.models.Model;

import java.awt.event.MouseEvent;

public class Tool {
    private Model model;

    public Tool(Model model) {
        this.model = model;
    }

    public void update() {

    }

    /**
     * Handle input
     */

    public void mousePressed(MouseEvent e) {
        model.setSelected(true);
    }

    public void mouseReleased(MouseEvent e) {
        model.setSelected(false);
    }

    public void mouseDragged(MouseEvent e) {
        if (model.isSelected()) {
            model.setX(e.getX() - model.getW() / 2);
            model.setY(e.getY() - model.getH() / 2);
        }
    }
}
