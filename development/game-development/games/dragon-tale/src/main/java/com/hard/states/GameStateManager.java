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
        try {
            gameStates[currentState].update();
        } catch (Exception e) {

        }
    }

    public void draw(Graphics2D graphics) {
        try {
            gameStates[currentState].draw(graphics);
        } catch (Exception e) {

        }
    }

    public void keyPressed(int k) {
        gameStates[currentState].keyPressed(k);
    }

    public void keyReleased(int k) {
        gameStates[currentState].keyReleased(k);
    }
}
