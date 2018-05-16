package com.hard.states;

import com.hard.tiles.Background;

import java.awt.*;
import java.awt.event.KeyEvent;

public class MenuState extends GameState {
    private Background background;

    private int currentChoice = 0;
    private String[] options = {
            "Start",
            "Help",
            "Quit"
    };

    private Color titleColor;
    private Font titleFont;

    private Font font;

    public MenuState(GameStateManager gameStateManager) {
        this.gameStateManager = gameStateManager;

        background = new Background("/backgrounds/menu_bg.gif", 1);
        background.setVector(-0.1, 0);

        titleColor = new Color(128, 0, 0);
        titleFont = new Font("Century Gothic", Font.PLAIN, 28);

        font = new Font("Arial", Font.PLAIN, 12);
    }

    @Override
    public void init() {

    }

    @Override
    public void update() {
        background.update();
    }

    @Override
    public void draw(Graphics2D graphics) {
        // draw background
        background.draw(graphics);

        // draw title
        graphics.setColor(titleColor);
        graphics.setFont(titleFont);
        graphics.drawString("Dragon Tale", 80, 70);

        // draw menu options
        graphics.setFont(font);
        for (int i = 0; i < options.length; i++) {
            if (i == currentChoice)
                graphics.setColor(Color.BLACK);
            else
                graphics.setColor(Color.RED);

            graphics.drawString(options[i], 145, 140 + i * 15);
        }
    }

    @Override
    public void keyPressed(int key) {
        if (key == KeyEvent.VK_ENTER)
            select();

        if (key == KeyEvent.VK_UP) {
            currentChoice--;

            if (currentChoice == -1)
                currentChoice = options.length - 1;
        }

        if (key == KeyEvent.VK_DOWN) {
            currentChoice++;

            if (currentChoice == options.length)
                currentChoice = 0;
        }
    }

    @Override
    public void keyReleased(int key) {

    }

    private void select() {
        switch (currentChoice) {
            case 0:
                gameStateManager.setState(GameStateManager.LEVEL_1_STATE);
                break;
            case 1:
                // help
                break;
            case 2:
                System.exit(0);
                break;
        }
    }
}
