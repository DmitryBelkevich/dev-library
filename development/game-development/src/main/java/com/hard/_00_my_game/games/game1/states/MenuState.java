package com.hard._00_my_game.games.game1.states;

import com.hard._00_my_game.games.GameState;
import com.hard._00_my_game.games.GameStateManager;
import com.hard._00_my_game.games.game1.entities.Background;
import com.hard._00_my_game.games.game1.entities.Entity;
import com.hard._00_my_game.games.game1.entities.Text;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Collection;

public class MenuState extends GameState {
    private Entity background;
    private Text menu;
    private Collection<Text> options;

    public MenuState(GameStateManager gameStateManager) {
        super(gameStateManager);

        this.background = new Background(0, 0);
        this.menu = new Text("Menu", 100, 100);

        this.options = new ArrayList<>();
        this.options.add(new Text("start", 100, 110));
        this.options.add(new Text("help", 100, 120));
        this.options.add(new Text("quit", 100, 130));
    }

    @Override
    public void update() {
        background.update();
        menu.update();

        for (Text text : options)
            text.update();
    }

    @Override
    public void draw(Graphics graphics) {
        background.draw(graphics);
        menu.draw(graphics);

        for (Text text : options)
            text.draw(graphics);
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
