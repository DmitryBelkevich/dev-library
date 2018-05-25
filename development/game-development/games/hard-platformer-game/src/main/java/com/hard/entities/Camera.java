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
        x += 5.0;System.out.println("l");
    }

    public void moveRight() {
        x -= 5.0 ;System.out.println("r");
    }

    public void moveUp() {
        y += 5.0 ;System.out.println("up");
    }

    public void moveDown() {
        y -= 5.0;System.out.println("dwn");
    }
}
