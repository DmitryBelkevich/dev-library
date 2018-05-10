package com.hard._02_GameStateManager;

public class Main {
    public static void main(String[] args) {
        Game game = new Game();

        game.run();
    }
}

/**
 * --- State pattern ---
 */

/**
 * Game state (State)
 */

abstract class GameState {
    protected GameStateManager gameStateManager;

    public GameState(GameStateManager gameStateManager) {
        this.gameStateManager = gameStateManager;
    }

    public abstract void operation();
}

class MenuState extends GameState {
    public MenuState(GameStateManager gameStateManager) {
        super(gameStateManager);
    }

    @Override
    public void operation() {
        System.out.println("operation MenuState");
        gameStateManager.setGameState(new PlayState(gameStateManager));
    }
}

class PlayState extends GameState {
    public PlayState(GameStateManager gameStateManager) {
        super(gameStateManager);
    }

    @Override
    public void operation() {
        System.out.println("operation PlayState");
        gameStateManager.setGameState(new GameOverState(gameStateManager));
    }
}

class GameOverState extends GameState {
    public GameOverState(GameStateManager gameStateManager) {
        super(gameStateManager);
    }

    @Override
    public void operation() {
        System.out.println("operation GameOverState");
        gameStateManager.setGameState(new MenuState(gameStateManager));
    }
}

/**
 * GameStateManager (Context)
 */

class GameStateManager {
    private GameState gameState;

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public void operation() {
        gameState.operation();
    }
}

/**
 * Game core (Client to context)
 */

class Game {
    private GameStateManager gameStateManager = new GameStateManager();

    public void run() {
        gameStateManager.setGameState(new MenuState(gameStateManager));

        gameStateManager.operation();
        gameStateManager.operation();
        gameStateManager.operation();
    }
}
