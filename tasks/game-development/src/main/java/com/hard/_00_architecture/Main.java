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
    protected StateManager stateManager;
    protected Saver saver;

    public State(StateManager stateManager, Saver saver) {
        this.stateManager = stateManager;
        this.saver = saver;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public abstract void update();
}

class MenuState extends State {
    public MenuState(StateManager stateManager, Saver saver) {
        super(stateManager, saver);
    }

    @Override
    public void update() {
        System.out.println("MenuState");
        stateManager.setState(new GameState(stateManager, saver));
    }
}

class GameState extends State {
    public GameState(StateManager stateManager, Saver saver) {
        super(stateManager, saver);
    }

    @Override
    public void update() {
        System.out.println("GameState");
        stateManager.setState(new GameOverState(stateManager, saver));
    }
}

class GameOverState extends State {
    public GameOverState(StateManager stateManager, Saver saver) {
        super(stateManager, saver);
    }

    @Override
    public void update() {
        System.out.println("GameOverState");
        stateManager.setState(new MenuState(stateManager, saver));
    }
}

class StateManager {
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
    protected StateManager stateManager;
    protected Saver saver;

    public void run() {
        update();
    }

    abstract void update();
}

class Game1 extends Game {
    public Game1() {
        stateManager = new StateManager();
        saver = new Saver();

        stateManager.setState(new MenuState(stateManager, saver));
    }

    @Override
    void update() {
        stateManager.update();
        stateManager.update();
        stateManager.update();
        stateManager.update();
    }
}

class GameFactory {
    public Game getGame(String title) {
        switch (title) {
            case "Game1":
                return new Game1();
        }

        return null;
    }
}
