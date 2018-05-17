package com.hard._00_my_template._01.games.game1;

import com.hard._00_my_template._01.games.Game;
import com.hard._00_my_template._01.games.GameState;
import com.hard._00_my_template._01.games.game1.states.GameOverState;
import com.hard._00_my_template._01.games.game1.states.Level1State;
import com.hard._00_my_template._01.games.game1.states.MenuState;

import java.awt.*;

public class Game1 extends Game {
    public Game1() {
        MenuState menuState = new MenuState(this.gameStateManager);
        this.gameStateManager.addGameState(menuState);
        this.gameStateManager.addGameState(new Level1State(this.gameStateManager));
        this.gameStateManager.addGameState(new GameOverState(this.gameStateManager));

        this.gameStateManager.setCurrentGameState(menuState);
    }

    @Override
    public void run() {
        while (true) {
            update();
            draw();
            display();

            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void update() {
        GameState gameState = gameStateManager.getCurrentGameState();
        gameState.update();
    }

    @Override
    public void draw() {
        GameState gameState = gameStateManager.getCurrentGameState();
        gameState.draw(graphics);
    }

    public void display() {
        Graphics graphics = frame.getGraphics();
        graphics.drawImage(bufferedImage, 0, 0, null);
        graphics.dispose();
    }
}
