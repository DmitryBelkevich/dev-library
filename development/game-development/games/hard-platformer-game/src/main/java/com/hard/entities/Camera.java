package com.hard.entities;

import java.awt.*;

public class Camera extends AbstractEntity {
    @Override
    public void update(double time) {

    }

    @Override
    public void draw(Graphics graphics) {

    }

    public void moveLeft() {
        x += 0.1;
    }

    public void moveRight() {
        x -= 0.1;
    }

    public void moveUp() {
        y += 0.1;
    }

    public void moveDown() {
        y -= 0.1;
    }
}
