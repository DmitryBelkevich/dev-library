package com.hard._00_my_game.games;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GameStateManager {
    private List<GameState> gameStates;
    private GameState currentGameState;

    public GameStateManager() {
        this.gameStates = new ArrayList<>();
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

    public void update() {
        currentGameState.update();
    }

    public void draw(Graphics graphics) {
        currentGameState.draw(graphics);
    }
}
