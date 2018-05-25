package com.hard.entities;

import com.hard.Tile;

import java.awt.*;

public class TileMap extends AbstractEntity {
    private int h;
    private int w;

    private char[][] chars;
    private Tile[][] tiles;

    // entities
    private Camera camera;

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

    public Camera getCamera() {
        return camera;
    }

    public void setCamera(Camera camera) {
        this.camera = camera;
    }

    @Override
    public void update(double time) {

    }

    @Override
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

                graphics.fillRect((int) (x + j * Tile.W - camera.getX()), (int) (y + i * Tile.H - camera.getY()), Tile.W, Tile.H);
            }
        }
    }
}
