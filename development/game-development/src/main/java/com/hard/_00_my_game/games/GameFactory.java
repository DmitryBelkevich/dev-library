package com.hard._00_my_game.games;

import com.hard._00_my_game.games.game1.Game1;

public class GameFactory {
    public Game getGame(String title) throws Exception {
        switch (title) {
            case "Game1":
                return new Game1();
            default:
                throw new Exception("the game " + title + "is not found");
        }
    }
}
