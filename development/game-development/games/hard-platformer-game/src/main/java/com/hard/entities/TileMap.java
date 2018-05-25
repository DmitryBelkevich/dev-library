package com.hard.entities;

import com.hard.Camera;
import com.hard.Tile;

import java.awt.*;

public class TileMap {
    private int h;
    private int w;

    private char[][] charsTilemap;

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

    public char[][] getCharsTilemap() {
        return charsTilemap;
    }

    public void setCharsTilemap(char[][] charsTilemap) {
        this.charsTilemap = charsTilemap;
    }

    public void update(double time) {

    }

    public void draw(Graphics graphics) {
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                char[] row = charsTilemap[i];
                char tile = row[j];

                if (tile == 'B')
                    graphics.setColor(new Color(100, 100, 100, 255));

                if (tile == '0')
                    graphics.setColor(new Color(0, 255, 0, 255));

                if (tile == ' ')
                    continue;

                graphics.fillRect((int) (j * Tile.W - Camera.x), (int) (i * Tile.H - Camera.y), Tile.W, Tile.H);
            }
        }
    }
}
