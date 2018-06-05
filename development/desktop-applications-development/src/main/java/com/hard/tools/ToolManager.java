package com.hard.tools;

import com.hard.models.Shape;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ToolManager {
    private List<Tool> tools;
    private int currentTool;

    public ToolManager(Collection<Shape> shapes) {
        tools = new ArrayList<>();

        tools.add(new MoveTool(shapes));
        tools.add(new ScaleTool(shapes));
    }

    public void addTool(Tool tool) {
        tools.add(tool);
    }

    public void setCurrentTool(int currentTool) {
        this.currentTool = currentTool;
    }

    public Tool getCurrentTool() {
        Tool tool = tools.get(currentTool);
        return tool;
    }

    public void update() {
        Tool tool = tools.get(currentTool);
        tool.update();
    }

    /**
     * Handle input
     */

    public void mousePressed(MouseEvent e) {
        Tool tool = tools.get(currentTool);
        tool.mousePressed(e);
    }

    public void mouseReleased(MouseEvent e) {
        Tool tool = tools.get(currentTool);
        tool.mouseReleased(e);
    }

    public void mouseMoved(MouseEvent e) {
        Tool tool = tools.get(currentTool);
        tool.mouseMoved(e);
    }

    public void mouseDragged(MouseEvent e) {
        Tool tool = tools.get(currentTool);
        tool.mouseDragged(e);
    }

    public void keyPressed(KeyEvent e) {
        Tool tool = tools.get(currentTool);
        tool.keyPressed(e);
    }

    public void keyReleased(KeyEvent e) {
        Tool tool = tools.get(currentTool);
        tool.keyReleased(e);
    }
}
