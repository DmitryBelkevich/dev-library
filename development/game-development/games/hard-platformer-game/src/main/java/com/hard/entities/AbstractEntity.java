package com.hard.entities;

import java.awt.*;

public abstract class AbstractEntity {
    protected double x;
    protected double y;

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public abstract void update(double time);

    public abstract void draw(Graphics graphics);
}
