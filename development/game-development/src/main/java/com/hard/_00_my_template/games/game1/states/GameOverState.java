package com.hard._00_my_template.games.game1.states;

import com.hard._00_my_template.GameFrame;
import com.hard._00_my_template.games.GameState;
import com.hard._00_my_template.games.GameStateManager;
import com.hard._00_my_template.games.game1.entities.Background;
import com.hard._00_my_template.games.game1.entities.Entity;
import com.hard._00_my_template.games.game1.entities.Text;

import java.awt.*;
import java.awt.event.KeyEvent;

public class GameOverState extends GameState {
    private Entity background;
    private Text menu;

    public GameOverState(GameStateManager gameStateManager) {
        super(gameStateManager);

        this.background = new Background(0, 0);
        this.menu = new Text("Game Over", GameFrame.WIDTH / 2, GameFrame.HEIGHT / 2);
    }

    @Override
    public void update() {
        background.update();
        menu.update();
    }

    @Override
    public void draw(Graphics graphics) {
        background.draw(graphics);
        menu.draw(graphics);
    }

    @Override
    public void keyPressed(int key) {
        if (key == KeyEvent.VK_NUMPAD0) {
            GameState gameState = gameStateManager.getGameState(0);
            gameStateManager.setCurrentGameState(gameState);
        }

        if (key == KeyEvent.VK_NUMPAD1) {
            GameState gameState = gameStateManager.getGameState(1);
            gameStateManager.setCurrentGameState(gameState);
        }

        if (key == KeyEvent.VK_NUMPAD2) {
            GameState gameState = gameStateManager.getGameState(2);
            gameStateManager.setCurrentGameState(gameState);
        }

        // load

        if (key == KeyEvent.VK_L)
            gameStateManager.load(0);
    }

    @Override
    public void keyReleased(int key) {

    }
}
