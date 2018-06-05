package com.hard.controllers;

import com.hard.models.Model;
import com.hard.views.EditorView;

import java.awt.event.MouseEvent;

public class Editor {
    private Model model;
    private EditorView editorView;

    public Editor(Model model) {
        this.model = model;

        editorView = new EditorView(this, model);
    }

    public void run() {
        editorView.run();

        while (true) {
            update();
        }
    }

    public void update() {
//        model.x++;
//        model.y++;
//
//        try {
//            Thread.sleep(20);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
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
