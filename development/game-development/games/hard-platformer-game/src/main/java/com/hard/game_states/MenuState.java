package com.hard.game_states;

import com.hard.config.Screen;

import java.awt.*;
import java.awt.event.KeyEvent;

public class MenuState extends GameState {
    // state
    private String[] options;
    private int currentOption;

    public MenuState(GameStateManager gameStateManager) {
        super(gameStateManager);

        // state
        options = new String[]{
                "Start",
                "Help",
                "Quit"
        };
        currentOption = 0;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();

        if (keyCode == KeyEvent.VK_UP) {
            currentOption--;
        }

        if (keyCode == KeyEvent.VK_DOWN) {
            currentOption++;
        }

        if (keyCode == KeyEvent.VK_ENTER) {
            if (currentOption == 0) {
                GameState gameState = gameStateManager.getGameState(1);
                gameStateManager.setCurrentGameState(gameState);
            }

            if (currentOption == 1) {

            }

            if (currentOption == 2) {
                System.exit(0);
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void update(double time) {
        if (currentOption >= options.length) {
            currentOption = 0;
        }

        if (currentOption < 0) {
            currentOption = options.length - 1;
        }
    }

    @Override
    public void draw(Graphics graphics) {
        for (int i = 0; i < options.length; i++) {
            if (i == currentOption)
                graphics.setColor(new Color(255, 0, 0, 255));
            else if (i != currentOption)
                graphics.setColor(new Color(0, 255, 0, 255));

            String option = options[i];
            graphics.drawString(option, Screen.WIDTH / 2, Screen.HEIGHT / 2 - 40 * i);
        }
    }
}
