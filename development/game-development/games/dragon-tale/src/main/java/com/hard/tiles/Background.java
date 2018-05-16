package com.hard.tiles;

import com.hard.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class Background {
    private double x;
    private double y;
    private double dx;
    private double dy;

    private double moveScale;

    private BufferedImage image;

    public Background(String path, double moveScale) {
        Class<?> clazz = this.getClass();
        InputStream inputStream = clazz.getResourceAsStream(path);
        try {
            image = ImageIO.read(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.moveScale = moveScale;
    }

    public void setPosition(double x, double y) {
        this.x = (x * moveScale) % GamePanel.WIDTH;
        this.y = (y * moveScale) % GamePanel.HEIGHT;
    }

    public void setVector(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    public void update() {
        x += dx;
        y += dy;
    }

    public void draw(Graphics2D graphics) {
        graphics.drawImage(image, (int) x, (int) y, null);

        if (x < 0)
            graphics.drawImage(image, (int) x + GamePanel.WIDTH, (int) y, null);

        if (x > 0)
            graphics.drawImage(image, (int) x - GamePanel.WIDTH, (int) y, null);
    }
}
