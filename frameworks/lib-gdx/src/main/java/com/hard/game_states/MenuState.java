package com.hard.game_states;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.hard.config.Screen;

public class MenuState extends GameState {
    // entities

    // graphics
    private BitmapFont font;

    // state
    private String[] options;
    private int currentOption;

    public MenuState(GameStateManager gameStateManager) {
        super(gameStateManager);

        // entities

        // graphics
        font = new BitmapFont();

        // state
        options = new String[]{
                "Start",
                "Help",
                "Quit"
        };
        currentOption = 0;
    }

    @Override
    public void handleInput(Input input) {
        if (input.isKeyJustPressed(Input.Keys.UP)) {
            currentOption--;
        }

        if (input.isKeyJustPressed(Input.Keys.DOWN)) {
            currentOption++;
        }

        if (input.isKeyJustPressed(Input.Keys.ENTER)) {
            if (currentOption == 0) {
                GameState gameState = gameStateManager.getGameState(1);
                gameStateManager.setCurrentGameState(gameState);
            }

            if (currentOption == 1) {

            }

            if (currentOption == 2) {

            }
        }
    }

    @Override
    public void update(float time) {
        if (currentOption >= options.length) {
            currentOption = 0;
        }

        if (currentOption < 0) {
            currentOption = options.length - 1;
        }
    }

    @Override
    public void draw(SpriteBatch spriteBatch, ShapeRenderer shapeRenderer) {
        spriteBatch.begin();
        for (int i = 0; i < options.length; i++) {
            if (i == currentOption)
                font.setColor(Color.RED);
            else if (i != currentOption)
                font.setColor(Color.GREEN);

            String option = options[i];
            font.draw(spriteBatch, option, Screen.WIDTH / 2, Screen.HEIGHT / 2 - 40 * i);
        }
        spriteBatch.end();

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.RED);
        shapeRenderer.circle(Screen.WIDTH / 2 - 20, Screen.HEIGHT / 2 + 0, 10);
        shapeRenderer.end();

        spriteBatch.setProjectionMatrix(camera.combined);
        shapeRenderer.setProjectionMatrix(camera.combined);
    }

    @Override
    public void dispose() {
        font.dispose();
    }
}
