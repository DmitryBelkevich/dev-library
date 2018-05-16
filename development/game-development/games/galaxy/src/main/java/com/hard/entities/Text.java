package com.hard.entities;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class Text {
    private double x;
    private double y;
    private String str;
    private long time;

    private long start;

    public Text(double x, double y, String str, long time) {
        this.x = x;
        this.y = y;
        this.time = time;
        this.str = str;

        start = System.nanoTime();
    }

    public boolean update() {
        long elapsed = (System.nanoTime() - start) / 1000000;

        if (elapsed > time)
            return true;

        return false;
    }

    public void draw(Graphics2D graphics) {
        graphics.setFont(new Font("Century Gothic", Font.PLAIN, 12));
        long elapsed = (System.nanoTime() - start) / 1000000;
        int alpha = (int) (255 * Math.sin(Math.PI * elapsed / time));
        if (alpha > 255)
            alpha = 255;
        graphics.setColor(new Color(255, 255, 255, alpha));
        FontMetrics fontMetrics = graphics.getFontMetrics();
        Rectangle2D rectangle = fontMetrics.getStringBounds(str, graphics);
        int length = (int) rectangle.getWidth();
        graphics.drawString(str, (int) (x - (length / 2)), (int) y);
    }
}
