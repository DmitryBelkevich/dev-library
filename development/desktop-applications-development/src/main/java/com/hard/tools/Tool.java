package com.hard.tools;

import com.hard.models.Shape;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.Collection;

public abstract class Tool {
    protected Collection<Shape> shapes;

    public Tool(Collection<Shape> shapes) {
        this.shapes = shapes;
    }

    public abstract void update();

    public abstract void draw();

    /**
     * Handle input
     */

    public abstract void mousePressed(MouseEvent e);

    public abstract void mouseReleased(MouseEvent e);

    public abstract void mouseMoved(MouseEvent e);

    public abstract void mouseDragged(MouseEvent e);

    public abstract void keyPressed(KeyEvent e);

    public abstract void keyReleased(KeyEvent e);
}
