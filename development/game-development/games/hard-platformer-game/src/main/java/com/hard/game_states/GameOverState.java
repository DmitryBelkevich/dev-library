package com.hard.game_states;

import com.hard.config.Screen;

import java.awt.*;
import java.awt.event.KeyEvent;

public class GameOverState extends GameState {
    // state
    private String str;
    private String score;

    public GameOverState(GameStateManager gameStateManager) {
        super(gameStateManager);

        // state
        str = "Game over";
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void update(double time) {

    }

    @Override
    public void draw(Graphics graphics) {
        graphics.setColor(new Color(255, 255, 255, 255));

        graphics.drawString(str, Screen.WIDTH / 2, Screen.HEIGHT / 2);
    }
}
