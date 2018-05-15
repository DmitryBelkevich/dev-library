package com.hard._00_my_game.games.game1.states;

import com.hard._00_my_game.GameFrame;
import com.hard._00_my_game.games.GameState;
import com.hard._00_my_game.games.GameStateManager;
import com.hard._00_my_game.games.game1.entities.Background;
import com.hard._00_my_game.games.game1.entities.Entity;
import com.hard._00_my_game.games.game1.entities.Text;

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
    }

    @Override
    public void keyReleased(int key) {

    }
}
