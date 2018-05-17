package com.hard._00_my_template._01.repository;

import com.hard._00_my_template._01.games.GameState;

public class File {
    private final GameState gameState;

    public File(GameState gameState) {
        this.gameState = gameState;
    }

    public GameState getGameState() {
        return gameState;
    }
}
