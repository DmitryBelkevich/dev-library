package com.hard._00_my_template._01.games.game1.states;

import com.hard._00_my_template._01.games.GameState;
import com.hard._00_my_template._01.games.GameStateManager;
import com.hard._00_my_template._01.games.game1.entities.Background;
import com.hard._00_my_template._01.games.game1.entities.Entity;
import com.hard._00_my_template._01.games.game1.entities.Player;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Level1State extends GameState {
    private Entity background;
    private Entity player;

    public Level1State(GameStateManager gameStateManager) {
        super(gameStateManager);

        this.background = new Background(0, 0);
        this.player = new Player(0, 0);
    }

    @Override
    public void update() {
        background.update();
        player.update();
    }

    @Override
    public void draw(Graphics graphics) {
        background.draw(graphics);
        player.draw(graphics);
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

        // save

        if (key == KeyEvent.VK_S)
            gameStateManager.save(this);

        // load

        if (key == KeyEvent.VK_L)
            gameStateManager.load(0);
    }

    @Override
    public void keyReleased(int key) {

    }
}
