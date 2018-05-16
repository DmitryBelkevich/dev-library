package com.hard.entities;

import com.hard.GamePanel;

import java.awt.*;

public class PowerUp {
    private double x;
    private double y;
    private int r;

    private int type;
    private Color color1;

    // 1 -- +1 life
    // 2 -- +1 power
    // 3 -- +2 power
    // 4 -- slow down time

    public PowerUp(double x, double y, int type) {
        this.x = x;
        this.y = y;

        this.type = type;

        if (type == 1) {
            color1 = Color.PINK;
            r = 3;
        }

        if (type == 2) {
            color1 = Color.YELLOW;
            r = 3;
        }

        if (type == 3) {
            color1 = Color.YELLOW;
            r = 5;
        }

        if (type == 4) {
            color1 = Color.WHITE;
            r = 3;
        }
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

    public int getType() {
        return type;
    }

    public boolean update() {
        y += 2;

        if (y > GamePanel.HEIGHT + r)
            return true;

        return false;
    }

    public void draw(Graphics2D graphics) {
        graphics.setColor(color1);
        graphics.fillRect((int) (x - r), (int) (y - r), 2 * r, 2 * r);

        graphics.setStroke(new BasicStroke(3));
        graphics.setColor(color1.darker());
        graphics.drawRect((int) (x - r), (int) (y - r), 2 * r, 2 * r);
        graphics.setStroke(new BasicStroke(1));
    }
}
