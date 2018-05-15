package com.hard._00_my_game.games.game1.states;

import com.hard._00_my_game.GameFrame;
import com.hard._00_my_game.games.GameState;
import com.hard._00_my_game.games.GameStateManager;
import com.hard._00_my_game.games.game1.entities.Background;
import com.hard._00_my_game.games.game1.entities.Entity;
import com.hard._00_my_game.games.game1.entities.Text;

import java.awt.*;

public class GameOverState extends GameState {
    private Entity background;
    private Text menu;

    public GameOverState(GameStateManager gameStateManager) {
        super(gameStateManager);

        this.background = new Background(0, 0);
        this.menu = new Text("Game Over", GameFrame.WIDTH / 2, GameFrame.HEIGHT / 2);
    }

    int i = 0;

    @Override
    public void update() {
//        System.out.println("Game Over State");

        if (i++ > 200) {
            i = 0;

            GameState gameState = this.gameStateManager.getGameState(0);
            this.gameStateManager.setCurrentGameState(gameState);
        }

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

    }

    @Override
    public void keyReleased(int key) {

    }
}
