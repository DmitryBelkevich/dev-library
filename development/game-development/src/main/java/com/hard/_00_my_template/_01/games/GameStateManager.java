package com.hard._00_my_template._01.games;

import com.hard._00_my_template._01.repository.File;
import com.hard._00_my_template._01.repository.GameStateSaver;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GameStateManager {
    private List<GameState> gameStates;
    private GameState currentGameState;

    private GameStateSaver gameStateSaver;

    public GameStateManager() {
        this.gameStates = new ArrayList<>();
        this.gameStateSaver = new GameStateSaver();
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

    public void save(GameState gameState) {
        File file = new File(gameState);
        gameStateSaver.save(file);
    }

    public void load(int i) {
        File file = gameStateSaver.getFile(i);
        currentGameState = file.getGameState();
    }

    public void update() {
        currentGameState.update();
    }

    public void draw(Graphics graphics) {
        currentGameState.draw(graphics);
    }

    public void keyPressed(int key) {
        currentGameState.keyPressed(key);
    }

    public void keyReleased(int key) {
        currentGameState.keyReleased(key);
    }
}
