package com.hard.game_states;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.hard.config.Screen;

public class GameOverState extends GameState {
    // graphics
    private BitmapFont font;

    // entities

    // state
    private String str;
    private String score;

    public GameOverState(GameStateManager gameStateManager) {
        super(gameStateManager);

        // graphics
        font = new BitmapFont();

        // entities

        // state
        str = "Game over";
    }

    @Override
    public void handleInput(Input input) {
        if (input.isKeyJustPressed(Input.Keys.ENTER)) {
            GameState gameState = gameStateManager.getGameState(0);
            gameStateManager.setCurrentGameState(gameState);
        }
    }

    @Override
    public void update(float time) {

    }

    @Override
    public void draw(SpriteBatch spriteBatch, ShapeRenderer shapeRenderer) {
        spriteBatch.begin();
        font.draw(spriteBatch, str, Screen.WIDTH / 2, Screen.HEIGHT / 2);
        spriteBatch.end();

        spriteBatch.setProjectionMatrix(camera.combined);
        shapeRenderer.setProjectionMatrix(camera.combined);
    }

    @Override
    public void dispose() {
        font.dispose();
    }
}
