package com.hard.models;

public class Circle extends Shape {
    private int r;

    public int getR() {
        return r;
    }

    public void setR(int r) {
        this.r = r;
    }

    @Override
    public boolean intersected(int mouseX, int mouseY) {
        double hypotenuse = Math.pow(Math.abs(x - mouseX), 2) + Math.pow(Math.abs(y - mouseY), 2);

        return hypotenuse <= r;
    }
}
