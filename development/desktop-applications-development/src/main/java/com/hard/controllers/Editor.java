package com.hard.controllers;

import com.hard.models.Shape;
import com.hard.views.EditorView;

import java.awt.event.MouseEvent;
import java.util.Collection;

public class Editor {
    private EditorView editorView;
    private Tool tool;

    public Editor(Collection<Shape> shapes) {
        editorView = new EditorView(this, shapes);

        tool = new MoveTool(shapes);
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

    public void mouseMoved(MouseEvent e) {
        tool.mouseMoved(e);
    }

    public void mouseDragged(MouseEvent e) {
        tool.mouseDragged(e);
    }
}
