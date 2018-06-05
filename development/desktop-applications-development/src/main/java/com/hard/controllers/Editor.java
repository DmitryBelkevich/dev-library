package com.hard.controllers;

import com.hard.models.Model;
import com.hard.views.EditorView;

import java.awt.event.MouseEvent;

public class Editor {
    private EditorView editorView;
    private Tool tool;

    public Editor(Model model) {
        editorView = new EditorView(this, model);

        tool = new Tool(model);
    }

    public void run() {
        editorView.run();

        while (true) {
            update();
        }
    }

    public void update() {
        tool.update();
    }

    /**
     * Handle input
     */

    public void mousePressed(MouseEvent e) {
        tool.mousePressed(e);
    }

    public void mouseReleased(MouseEvent e) {
        tool.mouseReleased(e);
    }

    public void mouseDragged(MouseEvent e) {
        tool.mouseDragged(e);
    }
}
