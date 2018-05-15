package com.hard._00_my_game.games.game1;

import com.hard._00_my_game.games.Game;
import com.hard._00_my_game.games.GameState;
import com.hard._00_my_game.games.game1.entities.Background;
import com.hard._00_my_game.games.game1.entities.Entity;
import com.hard._00_my_game.games.game1.entities.Player;
import com.hard._00_my_game.games.game1.states.GameOverState;
import com.hard._00_my_game.games.game1.states.Level1State;
import com.hard._00_my_game.games.game1.states.MenuState;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Game1 extends Game {
    public Game1() {
        this.gameFrame.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                int keyCode = e.getKeyCode();
                gameStateManager.keyPressed(keyCode);
            }

            @Override
            public void keyReleased(KeyEvent e) {
                int keyCode = e.getKeyCode();
                gameStateManager.keyReleased(keyCode);
            }
        });

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
