package com.hard.entities;

import java.awt.*;

public class Explosion {
    private double x;
    private double y;
    private int r;
    private int maxRadius;

    public Explosion(double x, double y, int r, int maxRadius) {
        this.x = x;
        this.y = y;
        this.r = r;
        this.maxRadius = maxRadius;
    }

    public boolean update() {
        r += 2;

        if (r >= maxRadius)
            return true;

        return false;
    }

    public void draw(Graphics2D graphics) {
        graphics.setColor(new Color(255, 255, 255, 128));
        graphics.setStroke(new BasicStroke(3));
        graphics.drawOval((int) (x - r), (int) (y - r), 2 * r, 2 * r);
        graphics.setStroke(new BasicStroke(1));
    }
}
