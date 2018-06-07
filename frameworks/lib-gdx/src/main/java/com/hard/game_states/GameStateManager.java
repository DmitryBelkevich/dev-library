package com.hard.game_states;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Array;

public class GameStateManager {
    private Array<GameState> gameStates;
    private GameState currentGameState;

    public GameStateManager() {
        this.gameStates = new Array<>();
    }

    public void addGameState(GameState gameState) {
        gameStates.add(gameState);
    }

    public GameState getGameState(int i) {
        return gameStates.get(i);
    }

    public GameState getCurrentGameState() {
        return currentGameState;
    }

    public void setCurrentGameState(GameState currentGameState) {
        this.currentGameState = currentGameState;
    }

    public void handleInput(Input input) {
        currentGameState.handleInput(input);
    }

    public void update(float time) {
        currentGameState.update(time);
    }

    public void draw(SpriteBatch spriteBatch, ShapeRenderer shapeRenderer) {
        currentGameState.draw(spriteBatch, shapeRenderer);
    }

    public void dispose() {
        currentGameState.dispose();
    }
}
