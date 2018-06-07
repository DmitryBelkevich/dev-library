package com.hard.game_states;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.hard.config.Screen;

public abstract class GameState {
    // game states
    protected GameStateManager gameStateManager;

    // entities

    // camera
    protected OrthographicCamera camera;

    public GameState(GameStateManager gameStateManager) {
        // game states
        this.gameStateManager = gameStateManager;

        // entities

        // camera
        camera = new OrthographicCamera();

        camera.setToOrtho(false, Screen.WIDTH, Screen.HEIGHT);
        camera.position.x = Screen.WIDTH / 2;
        camera.position.y = Screen.HEIGHT / 2;
    }

    public abstract void handleInput(Input input);

    public abstract void update(float time);

    public abstract void draw(SpriteBatch spriteBatch, ShapeRenderer shapeRenderer);

    public abstract void dispose();
}
