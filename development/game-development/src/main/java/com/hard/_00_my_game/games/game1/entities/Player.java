package com.hard._00_my_game.games.game1.entities;

import java.awt.*;

public class Player extends Entity {
    public Player(int x, int y) {
        super(x, y);
    }

    @Override
    public void update() {
        x++;
        y++;
    }

    @Override
    public void draw(Graphics graphics) {
        graphics.setColor(Color.RED);
        graphics.drawString("Hello World", x, y);
    }
}
