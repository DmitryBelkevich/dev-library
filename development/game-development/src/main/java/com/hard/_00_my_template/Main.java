package com.hard._00_my_template;

import com.hard._00_my_template.games.Game;
import com.hard._00_my_template.games.GameFactory;

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
