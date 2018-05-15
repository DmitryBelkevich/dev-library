package com.hard._00_my_game.games.game1.entities;

import java.awt.*;

public class Text extends Entity {
    private String text;

    public Text(String text, int x, int y) {
        super(x, y);
        this.text = text;
    }

    @Override
    public void update() {

    }

    @Override
    public void draw(Graphics graphics) {
        graphics.setColor(Color.RED);
        graphics.drawString(text, x, y);
    }
}
