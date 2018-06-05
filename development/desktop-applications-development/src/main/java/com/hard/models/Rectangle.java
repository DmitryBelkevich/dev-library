package com.hard.models;

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
    public boolean intersected(int mouseX, int mouseY) {
        return mouseX >= x &&
                mouseX <= x + w &&
                mouseY >= y &&
                mouseY <= y + h;
    }
}
