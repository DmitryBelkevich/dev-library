package com.hard._00_my_template.repository;

import com.hard._00_my_template.games.GameState;

public class File {
    private final GameState gameState;

    public File(GameState gameState) {
        this.gameState = gameState;
    }

    public GameState getGameState() {
        return gameState;
    }
}
