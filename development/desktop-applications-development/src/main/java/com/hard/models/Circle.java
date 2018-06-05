package com.hard.models;

import java.awt.*;

public class Circle extends Shape {
    private int r;

    public int getR() {
        return r;
    }

    public void setR(int r) {
        this.r = r;
    }

    @Override
    public void draw(Graphics graphics) {
        if (isSelected())
            graphics.setColor(new Color(0, 255, 0, 255));
        else
            graphics.setColor(new Color(255, 0, 0, 255));

        graphics.fillOval(x - r, y - r, r * 2, r * 2);
    }

    @Override
    public boolean intersected(int mouseX, int mouseY) {
        double hypotenuse = Math.pow(Math.abs(x - mouseX), 2) + Math.pow(Math.abs(y - mouseY), 2);

        return hypotenuse <= r;
    }
}
