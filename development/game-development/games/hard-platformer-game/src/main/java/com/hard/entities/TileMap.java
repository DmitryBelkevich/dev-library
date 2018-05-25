package com.hard.entities;

import com.hard.Camera;
import com.hard.Tile;
import com.hard.maps.Maps;

import java.awt.*;

public class TileMap {
    public static final int H = 20;
    public static final int W = 50;

    private char[][] charsTilemap;

    public TileMap() {
        String[] tilemap = Maps.stage1.level1;

        // create
        charsTilemap = new char[50][20];

        // fill
        for (int i = 0; i < tilemap.length; i++) {
            String row = tilemap[i];
            charsTilemap[i] = row.toCharArray();
        }
    }

    public char[][] getCharsTilemap() {
        return charsTilemap;
    }

    public void update(double time) {

    }

    public void draw(Graphics graphics) {
        for (int i = 0; i < TileMap.H; i++) {
            for (int j = 0; j < TileMap.W; j++) {
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
