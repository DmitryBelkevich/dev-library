package com.hard.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Entity {
    private float x;
    private float y;

    private Texture texture;

    public Entity() {
        texture = new Texture("badlogic.jpg");
    }

    public void update(float time) {
        x++;
        y++;
    }

    public void draw(SpriteBatch spriteBatch) {
        spriteBatch.begin();
        spriteBatch.draw(texture, x, y);
        spriteBatch.end();
    }

    public void dispose() {
        texture.dispose();
    }
}
