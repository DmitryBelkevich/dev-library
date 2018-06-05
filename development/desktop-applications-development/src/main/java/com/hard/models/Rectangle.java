package com.hard.models;

import java.awt.*;

public class Rectangle extends Shape {
    private int w;
    private int h;

    public int getW() {
        return w;
    }

    public void setW(int w) {
        this.w = w;
    }

    public int getH() {
        return h;
    }

    public void setH(int h) {
        this.h = h;
    }

    @Override
    public void draw(Graphics graphics) {
        if (isOver() && !isSelected())
            graphics.setColor(new Color(255, 255, 0, 255));
        else if (isSelected())
            graphics.setColor(new Color(0, 255, 0, 255));
        else
            graphics.setColor(new Color(255, 0, 0, 255));

        graphics.fillRect(x, y, w, h);
    }

    @Override
    public boolean intersected(int mouseX, int mouseY) {
        return mouseX >= x &&
                mouseX <= x + w &&
                mouseY >= y &&
                mouseY <= y + h;
    }
}
