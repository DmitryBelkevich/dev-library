package com.hard._00_architecture;

public class Main {
    public static void main(String[] args) {
        GameFactory gameFactory = new GameFactory();
        Game game = gameFactory.getGame("Game1");
        game.run();
    }
}

/**
 * Memento pattern
 */

final class File {
    private final GameState gameState;

    public File(GameState gameState) {
        this.gameState = gameState;
    }

    public GameState getGameState() {
        return gameState;
    }
}

class Saver {
    private File file;

    public File getFile() {
        return file;
    }

    public void saveFile(File file) {
        this.file = file;
    }
}

/**
 * State pattern
 */

abstract class GameState implements Cloneable {
    protected GameStateManager gameStateManager;
    protected Saver saver;

    public GameState(GameStateManager gameStateManager, Saver saver) {
        this.gameStateManager = gameStateManager;
        this.saver = saver;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public abstract void update();
}

class MenuState extends GameState {
    public MenuState(GameStateManager gameStateManager, Saver saver) {
        super(gameStateManager, saver);
    }

    @Override
    public void update() {
        System.out.println("MenuState");
        gameStateManager.setGameState(new PlayState(gameStateManager, saver));
    }
}

class PlayState extends GameState {
    public PlayState(GameStateManager gameStateManager, Saver saver) {
        super(gameStateManager, saver);
    }

    @Override
    public void update() {
        System.out.println("PlayState");
        gameStateManager.setGameState(new GameOverState(gameStateManager, saver));
    }
}

class GameOverState extends GameState {
    public GameOverState(GameStateManager gameStateManager, Saver saver) {
        super(gameStateManager, saver);
    }

    @Override
    public void update() {
        System.out.println("GameOverState");
        gameStateManager.setGameState(new MenuState(gameStateManager, saver));
    }
}

class GameStateManager {
    private GameState gameState;

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public GameState getGameState() {
        return gameState;
    }

    public void update() {
        gameState.update();
    }

    public File createFile() {
        GameState clonedState = null;
        try {
            clonedState = (GameState) gameState.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        return new File(clonedState);
    }

    public void load(File file) {
        gameState = file.getGameState();
    }
}

/**
 * Template-method pattern
 */

abstract class Game {
    public static final int WIDTH = 480;
    public static final int HEIGHT = 800;
    public static final String title = "Game";

    // image
    // graphic
    // music

    protected GameStateManager gameStateManager;
    protected Saver saver;

    public Game() {
        gameStateManager = new GameStateManager();
        saver = new Saver();

        gameStateManager.setGameState(new MenuState(gameStateManager, saver));
    }

    public void run() {
        update();
        update();
        update();
        update();
    }

    public void update() {
        gameStateManager.update();
    }
}

class Game1 extends Game {

}

/**
 * Factory
 */

class GameFactory {
    public Game getGame(String title) {
        switch (title) {
            case "Game1":
                return new Game1();
        }

        return null;
    }
}
