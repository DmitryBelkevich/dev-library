package com.hard.entities;

import com.hard.Tile;

import java.awt.*;

public class TileMap {
    private double x;
    private double y;

    private int h;
    private int w;

    private char[][] chars;
    private Tile[][] tiles;

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

    public int getH() {
        return h;
    }

    public void setH(int h) {
        this.h = h;
    }

    public int getW() {
        return w;
    }

    public void setW(int w) {
        this.w = w;
    }

    public char[][] getChars() {
        return chars;
    }

    public void setChars(char[][] chars) {
        this.chars = chars;
    }

    public int getColumnNumber(int x) {
        return (int) ((x - this.x) / Tile.W);
    }

    public int getRowNumber(int y) {
        return (int) ((y - this.y) / Tile.H);
    }

    public void update(double time) {

    }

    public void draw(Graphics graphics) {
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                char[] row = chars[i];
                char tile = row[j];

                if (tile == 'B')
                    graphics.setColor(new Color(100, 100, 100, 255));

                if (tile == '0')
                    graphics.setColor(new Color(0, 255, 0, 255));

                if (tile == ' ')
                    continue;

                graphics.fillRect((int) (x + j * Tile.W/* - Camera.x*/), (int) (y + i * Tile.H/* - Camera.y*/), Tile.W, Tile.H);
            }
        }
    }
}
