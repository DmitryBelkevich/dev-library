package com.hard._00_my_game.games.game1.states;

import com.hard._00_my_game.games.GameState;
import com.hard._00_my_game.games.GameStateManager;
import com.hard._00_my_game.games.game1.entities.Background;
import com.hard._00_my_game.games.game1.entities.Entity;
import com.hard._00_my_game.games.game1.entities.Player;

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
            this.gameStateManager.setCurrentGameState(gameState);
        }

        if (key == KeyEvent.VK_NUMPAD1) {
            GameState gameState = gameStateManager.getGameState(1);
            this.gameStateManager.setCurrentGameState(gameState);
        }

        if (key == KeyEvent.VK_NUMPAD2) {
            GameState gameState = gameStateManager.getGameState(2);
            this.gameStateManager.setCurrentGameState(gameState);
        }
    }

    @Override
    public void keyReleased(int key) {

    }
}
