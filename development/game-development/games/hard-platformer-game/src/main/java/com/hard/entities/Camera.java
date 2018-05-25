package com.hard.entities;

import java.awt.*;

public class Camera extends AbstractEntity {
    @Override
    public void update(double time) {

    }

    @Override
    public void draw(Graphics graphics) {

    }

    public void setPosition(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void moveLeft() {
        x -= 5.0;
    }

    public void moveRight() {
        x += 5.0;
    }

    public void moveUp() {
        y -= 5.0;
    }

    public void moveDown() {
        y += 5.0;
    }
}
