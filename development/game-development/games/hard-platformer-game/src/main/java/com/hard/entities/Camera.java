package com.hard.entities;

import java.awt.*;

public class Camera extends AbstractEntity {
    private int dx;
    private int dy;

    private boolean left;
    private boolean right;
    private boolean up;
    private boolean down;

    @Override
    public void update(double time) {
        if (left) {
            dx = -5;
        }

        if (right) {
            dx = 5;
        }

        if (up) {
            dy = -5;
        }

        if (down) {
            dy = 5;
        }

        x += dx * time;
        y += dy * time;

        dx = 0;
        dy = 0;
    }

    @Override
    public void draw(Graphics graphics) {

    }

    public void setPosition(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public boolean isLeft() {
        return left;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public boolean isRight() {
        return right;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public boolean isUp() {
        return up;
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    public boolean isDown() {
        return down;
    }

    public void setDown(boolean down) {
        this.down = down;
    }
}
