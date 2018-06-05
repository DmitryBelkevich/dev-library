package com.hard.controllers;

import com.hard.models.Shape;
import com.hard.tools.ToolManager;
import com.hard.views.EditorView;

import java.awt.event.MouseEvent;
import java.util.Collection;

public class Editor {
    private EditorView editorView;
    private ToolManager toolManager;

    public Editor(Collection<Shape> shapes) {
        editorView = new EditorView(this, shapes);

        toolManager = new ToolManager(shapes);
        toolManager.setCurrentTool(0);
    }

    public void run() {
        editorView.run();

        while (true) {
            update();
        }
    }

    public void update() {
        toolManager.update();
    }

    /**
     * Handle input
     */

    public void mousePressed(MouseEvent e) {
        toolManager.mousePressed(e);
    }

    public void mouseReleased(MouseEvent e) {
        toolManager.mouseReleased(e);
    }

    public void mouseMoved(MouseEvent e) {
        toolManager.mouseMoved(e);
    }

    public void mouseDragged(MouseEvent e) {
        toolManager.mouseDragged(e);
    }
}
