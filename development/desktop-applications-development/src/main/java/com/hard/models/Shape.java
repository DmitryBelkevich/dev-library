package com.hard.models;

import java.awt.*;

public abstract class Shape {
    protected int x;
    protected int y;
    private boolean selected;

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public abstract void draw(Graphics graphics);

    public abstract boolean intersected(int mouseX, int mouseY);
}
