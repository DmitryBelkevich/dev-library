package com.hard._00_my_game.games.game1.states;

import com.hard._00_my_game.GameFrame;
import com.hard._00_my_game.games.GameState;
import com.hard._00_my_game.games.GameStateManager;
import com.hard._00_my_game.games.game1.entities.Background;
import com.hard._00_my_game.games.game1.entities.Entity;
import com.hard._00_my_game.games.game1.entities.Text;

import java.awt.*;
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

    int i = 0;

    @Override
    public void update() {
        System.out.println("MenuState");

        if (i++ > 200) {
            i = 0;

            GameState gameState = this.gameStateManager.getGameState(1);
            this.gameStateManager.setCurrentGameState(gameState);
        }

        background.update();
        menu.update();
    }

    @Override
    public void draw(Graphics graphics) {
        // background
        menu.draw(graphics);

        // options
        for (Text text : options)
            text.draw(graphics);
    }
}
