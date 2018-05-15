package com.hard._00_my_game.games.game1;

import com.hard._00_my_game.games.Game;
import com.hard._00_my_game.games.game1.entities.Background;
import com.hard._00_my_game.games.game1.entities.Entity;
import com.hard._00_my_game.games.game1.entities.Player;
import com.hard._00_my_game.games.game1.states.GameOverState;
import com.hard._00_my_game.games.game1.states.Level1State;
import com.hard._00_my_game.games.game1.states.MenuState;

import java.awt.*;

public class Game1 extends Game {
    private Entity background;
    private Entity player;

    public Game1() {
        this.player = new Player(0, 0);
        this.background = new Background(0, 0);

        this.gameStateManager.addGameState(new MenuState());
        this.gameStateManager.addGameState(new Level1State());
        this.gameStateManager.addGameState(new GameOverState());
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
        background.update();
        player.update();
    }

    @Override
    public void draw() {
        background.draw(graphics);
        player.draw(graphics);
    }

    public void display() {
        Graphics graphics = frame.getGraphics();
        graphics.drawImage(bufferedImage, 0, 0, null);
        graphics.dispose();
    }
}
