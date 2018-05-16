package com.hard.states;

import java.awt.*;

public class GameStateManager {
    private GameState[] gameStates;
    private int currentState;

    public static final int NUM_GAME_STATES = 2;
    public static final int MENU_STATE = 0;
    public static final int LEVEL_1_STATE = 1;

    public GameStateManager() {
        gameStates = new GameState[NUM_GAME_STATES];

        currentState = MENU_STATE;
        loadState(currentState);
    }

    private void loadState(int state) {
        switch (state) {
            case MENU_STATE:
                gameStates[state] = new MenuState(this);
                break;
            case LEVEL_1_STATE:
                gameStates[state] = new Level1State(this);
                break;
        }
    }

    private void unloadState(int state) {
        gameStates[state] = null;
    }

    public void setState(int state) {
        unloadState(currentState);
        currentState = state;
        loadState(currentState);
        //gameStates[currentState].init();
    }

    public void update() {
        GameState gameState = gameStates[currentState];
        try {
            gameState.update();
        } catch (Exception e) {

        }
    }

    public void draw(Graphics2D graphics) {
        GameState gameState = gameStates[currentState];
        try {
            gameState.draw(graphics);
        } catch (Exception e) {

        }
    }

    public void keyPressed(int key) {
        GameState gameState = gameStates[currentState];
        gameState.keyPressed(key);
    }

    public void keyReleased(int key) {
        GameState gameState = gameStates[currentState];
        gameState.keyReleased(key);
    }
}
