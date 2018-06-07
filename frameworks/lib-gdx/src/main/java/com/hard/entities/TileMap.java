package com.hard.entities;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;

public class TileMap {
    // state
    private Vector3 position;

    public TileMap() {
        position = new Vector3(0, 0, 0);
    }

    public void handleInput(Input input) {

    }

    public void update(float time) {

    }

    public void draw(SpriteBatch spriteBatch, ShapeRenderer shapeRenderer) {
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);

        shapeRenderer.setColor(Color.CYAN);
        shapeRenderer.rect(position.x, position.y, 32, 32);

        shapeRenderer.end();
    }

    public void dispose() {

    }
}
