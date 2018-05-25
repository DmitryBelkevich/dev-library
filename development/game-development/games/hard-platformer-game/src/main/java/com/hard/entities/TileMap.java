package com.hard.entities;

import com.hard.Camera;
import com.hard.Tile;
import com.hard.maps.Maps;

import java.awt.*;

public class TileMap {
    private final int h;
    private final int w;

    private char[][] charsTilemap;

    public TileMap() {
        String[] tilemap = Maps.stage1.level1;

        // create
        h = tilemap.length;
        w = tilemap[0].length();

        charsTilemap = new char[w][h];

        // fill
        for (int i = 0; i < tilemap.length; i++) {
            String row = tilemap[i];
            charsTilemap[i] = row.toCharArray();
        }
    }

    public int getH() {
        return h;
    }

    public int getW() {
        return w;
    }

    public char[][] getCharsTilemap() {
        return charsTilemap;
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
