package com.hard._00_my_game.games.game1.states;

import com.hard._00_my_game.games.GameState;
import com.hard._00_my_game.games.GameStateManager;
import com.hard._00_my_game.games.game1.entities.Background;
import com.hard._00_my_game.games.game1.entities.Entity;
import com.hard._00_my_game.games.game1.entities.Player;

import java.awt.*;

public class Level1State extends GameState {
    private Entity background;
    private Entity player;

    public Level1State(GameStateManager gameStateManager) {
        super(gameStateManager);

        this.background = new Background(0, 0);
        this.player = new Player(0, 0);
    }

    int i = 0;

    @Override
    public void update() {
        System.out.println("Level 1 State");

        if (i++ > 200) {
            i = 0;

            GameState gameState = this.gameStateManager.getGameState(2);
            this.gameStateManager.setCurrentGameState(gameState);
        }

        background.update();
        player.update();
    }

    @Override
    public void draw(Graphics graphics) {
        background.draw(graphics);
        player.draw(graphics);
    }
}
