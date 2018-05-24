package com.hard.entities;

import com.hard.Game;

import java.awt.*;

public class TileMap {
    public static final int H = 20;
    public static final int W = 50;

    public static final String[] tilemap = {
            "BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB",
            "B                                                B",
            "B                                                B",
            "B                                                B",
            "B                                      00000     B",
            "B                     BBBBB                      B",
            "B                                                B",
            "B                                                B",
            "B                                                B",
            "B         0000000             BBBBBBB            B",
            "B                                B          BBBBBB",
            "B                    BBBBBB      B               B",
            "B                                B               B",
            "B                                B               B",
            "B                                B    BBBB       B",
            "BBB             BBBBB            B               B",
            "B                                BB           BBBB",
            "B                                                B",
            "B                         BB                     B",
            "BBBBBBBBBBBBBBBB     BBBBBBBBBBBBBBBBBBBBBBBBBBBBB",
    };

    public void draw(Graphics graphics) {
        for (int i = 0; i < TileMap.H; i++) {
            for (int j = 0; j < TileMap.W; j++) {
                String row = tilemap[i];
                char tile = row.charAt(j);

                if (tile == 'B')
                    graphics.setColor(new Color(100, 100, 100, 255));

                if (tile == '0')
                    graphics.setColor(new Color(0, 255, 0, 255));

                if (tile == ' ')
                    continue;

                graphics.fillRect((int) (j * Game.W_TILE - Game.OFFSET_X), (int) (i * Game.H_TILE - Game.OFFSET_Y), Game.W_TILE, Game.H_TILE);
            }
        }
    }
}
