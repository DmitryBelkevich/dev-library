package com.hard._00_my_game.games.game1.entities;

import com.hard._00_my_game.GameFrame;

import java.awt.*;

public class Background extends Entity {
    public Background(int x, int y) {
        super(x, y);
    }

    @Override
    public void update() {

    }

    @Override
    public void draw(Graphics graphics) {
        graphics.setColor(new Color(0, 0, 0));
        graphics.fillRect(x, y, GameFrame.WIDTH, GameFrame.HEIGHT);
    }
}
