package com.hard;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.hard.entities.Entity;

public class Game1 extends ApplicationAdapter {
    private SpriteBatch spriteBatch;

    private Entity entity;

    @Override
    public void create() {
        Gdx.gl.glClearColor(1, 0, 0, 1);

        spriteBatch = new SpriteBatch();

        entity = new Entity();
    }

    @Override
    public void render() {
        Graphics graphics = Gdx.graphics;
        float time = graphics.getDeltaTime();

        update(time);
        clearScreen();
        draw(spriteBatch);
    }

    @Override
    public void dispose() {
        spriteBatch.dispose();
        entity.dispose();
    }

    public void update(float time) {
        entity.update(time);
    }

    public void clearScreen() {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }

    public void draw(SpriteBatch spriteBatch) {
        entity.draw(spriteBatch);
    }
}
