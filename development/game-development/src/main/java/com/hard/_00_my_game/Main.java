package com.hard._00_my_game;

import com.hard._00_my_game.games.Game;
import com.hard._00_my_game.games.GameFactory;

public class Main {
    public static void main(String[] args) {
        GameFactory gameFactory = new GameFactory();

        Game game = null;
        try {
            game = gameFactory.getGame("Game1");
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        }

        game.run();
    }
}
