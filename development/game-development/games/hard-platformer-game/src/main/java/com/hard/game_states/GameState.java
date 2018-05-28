package com.hard.game_states;

import java.awt.*;
import java.awt.event.KeyEvent;

public abstract class GameState {
    // game states
    protected GameStateManager gameStateManager;

    // entities

    public GameState(GameStateManager gameStateManager) {
        this.gameStateManager = gameStateManager;
    }

    public abstract void keyPressed(KeyEvent e);

    public abstract void keyReleased(KeyEvent e);

    public abstract void update(double time);

    public abstract void draw(Graphics graphics);
}
