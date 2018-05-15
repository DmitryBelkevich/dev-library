package com.hard._00_my_game.games;

import java.util.ArrayList;
import java.util.List;

public class GameStateManager {
    private List<GameState> gameStates;

    public GameStateManager() {
        this.gameStates = new ArrayList<>();
    }

    public void addGameState(GameState gameState) {
        gameStates.add(gameState);
    }
}
