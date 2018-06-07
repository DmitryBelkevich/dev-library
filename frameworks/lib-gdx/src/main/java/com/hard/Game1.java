package com.hard;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.hard.game_states.*;

public class Game1 extends ApplicationAdapter {
    // game states
    private GameStateManager gameStateManager;

    // graphics
    private SpriteBatch spriteBatch;
    private ShapeRenderer shapeRenderer;

    @Override
    public void create() {
        Gdx.gl.glClearColor(0, 0, 0, 1);

        /**
         * game states
         */

        gameStateManager = new GameStateManager();

        GameState menuState = new MenuState(gameStateManager);
        GameState level1State = new Level1State(gameStateManager);
        GameState gameOverState = new GameOverState(gameStateManager);

        gameStateManager.addGameState(menuState);
        gameStateManager.addGameState(level1State);
        gameStateManager.addGameState(gameOverState);

        gameStateManager.setCurrentGameState(menuState);

        /**
         * graphics
         */

        spriteBatch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();
    }

    @Override
    public void render() {
        Graphics graphics = Gdx.graphics;
        float time = graphics.getDeltaTime();

        Input input = Gdx.input;
        handleInput(input);

        update(time);
        clearScreen();
        draw(spriteBatch);
    }

    public void handleInput(Input input) {
        gameStateManager.handleInput(input);
    }

    public void update(float time) {
        gameStateManager.update(time);
    }

    public void clearScreen() {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }

    public void draw(SpriteBatch spriteBatch) {
        gameStateManager.draw(spriteBatch, shapeRenderer);
    }

    @Override
    public void dispose() {
        gameStateManager.dispose();

        spriteBatch.dispose();
        shapeRenderer.dispose();
    }
}
