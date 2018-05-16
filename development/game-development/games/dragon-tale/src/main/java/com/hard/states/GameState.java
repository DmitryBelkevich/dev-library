package com.hard.states;

import java.awt.*;

public abstract class GameState {
    protected GameStateManager gameStateManager;

    public abstract void init();

    public abstract void update();

    public abstract void draw(Graphics2D graphics);

    public abstract void keyPressed(int key);

    public abstract void keyReleased(int key);
}
