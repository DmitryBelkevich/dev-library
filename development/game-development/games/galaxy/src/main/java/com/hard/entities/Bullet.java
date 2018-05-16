package com.hard.entities;

import com.hard.GamePanel;

import java.awt.*;

public class Bullet {
    private double x;
    private double y;
    private int r;

    private double dx;
    private double dy;
    private double rad;
    private double speed;

    private Color color1;

    public Bullet(double x, double y, double angle) {
        this.x = x;
        this.y = y;
        r = 2;

        rad = Math.toRadians(angle);
        speed = 10;
        dx = Math.cos(rad) * speed;
        dy = Math.sin(rad) * speed;

        color1 = Color.YELLOW;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public int getR() {
        return r;
    }

    public boolean update() {
        x += dx;
        y += dy;

        if (x < -r)
            return true;

        if (x > GamePanel.WIDTH + r)
            return true;

        if (y < -r)
            return true;

        if (y > GamePanel.HEIGHT + r)
            return true;

        return false;
    }

    public void draw(Graphics2D graphics) {
        graphics.setColor(color1);
        graphics.fillOval((int) (x - r), (int) (y - r), 2 * r, 2 * r);
    }
}
