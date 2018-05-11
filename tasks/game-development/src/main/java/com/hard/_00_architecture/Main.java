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
    private final State state;

    public File(State state) {
        this.state = state;
    }

    public State getState() {
        return state;
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

abstract class State implements Cloneable {
    protected GameStateManager gameStateManager;
    protected Saver saver;

    public State(GameStateManager gameStateManager, Saver saver) {
        this.gameStateManager = gameStateManager;
        this.saver = saver;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public abstract void update();
}

class MenuState extends State {
    public MenuState(GameStateManager gameStateManager, Saver saver) {
        super(gameStateManager, saver);
    }

    @Override
    public void update() {
        System.out.println("MenuState");
        gameStateManager.setState(new GameState(gameStateManager, saver));
    }
}

class GameState extends State {
    public GameState(GameStateManager gameStateManager, Saver saver) {
        super(gameStateManager, saver);
    }

    @Override
    public void update() {
        System.out.println("GameState");
        gameStateManager.setState(new GameOverState(gameStateManager, saver));
    }
}

class GameOverState extends State {
    public GameOverState(GameStateManager gameStateManager, Saver saver) {
        super(gameStateManager, saver);
    }

    @Override
    public void update() {
        System.out.println("GameOverState");
        gameStateManager.setState(new MenuState(gameStateManager, saver));
    }
}

class GameStateManager {
    private State state;

    public void setState(State state) {
        this.state = state;
    }

    public State getState() {
        return state;
    }

    public void update() {
        state.update();
    }

    public File createFile() {
        State clonedState = null;
        try {
            clonedState = (State) state.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        return new File(clonedState);
    }

    public void load(File file) {
        state = file.getState();
    }
}

/**
 * Template-method pattern
 */

abstract class Game {
    protected GameStateManager gameStateManager;
    protected Saver saver;

    public void run() {
        update();
        update();
        update();
        update();
    }

    abstract void update();
}

class Game1 extends Game {
    public Game1() {
        gameStateManager = new GameStateManager();
        saver = new Saver();

        gameStateManager.setState(new MenuState(gameStateManager, saver));
    }

    @Override
    void update() {
        gameStateManager.update();
    }
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
