package com.hard._01_architecture;

public class Main {
    public static void main(String[] args) {
        DesktopLauncher desktopLauncher = new DesktopLauncher();
        desktopLauncher.main();

//        AndroidLauncher androidLauncher = new AndroidLauncher();
//        androidLauncher.onCreate();

//        HtmlLauncher htmlLauncher = new HtmlLauncher();
//        htmlLauncher.create();
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
 * Facade pattern
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

    // Entities:
    // List<Player> players;
    // List<Enemy> enemies;
    // List<Bullet> bullets;
    // List<MovingPlatform> movingPlatforms;

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
        // players.update();
        // enemies.update();
        // bullets.update();
        // movingPlatforms.update();
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

/**
 * Config (Adapter)
 */

class DesktopConfig {
    public int width;
    public int height;
    public String title;
}

class AndroidConfig {

}

class HtmlConfig {
    public int width;
    public int height;
    public String title;
}

/**
 * Application (Target)
 */

class DesktopApplication {
    private Game game;
    private DesktopConfig config;

    public DesktopApplication(Game game, DesktopConfig config) {
        this.game = game;
        this.config = config;

        init();
    }

    public void init() {
        System.out.println("App has launched on Desktop");
        System.out.println("run swing frame");

        game.run();
    }
}

class AndroidApplication {
    private Game game;
    private AndroidConfig config;

    public AndroidApplication(Game game, AndroidConfig config) {
        this.game = game;
        this.config = config;

        init();
    }

    public void init() {
        System.out.println("App has launched on Android");
        System.out.println("run main activity");

        game.run();
    }
}

class HtmlApplication {
    private Game game;
    private HtmlConfig config;

    public HtmlApplication(Game game, HtmlConfig config) {
        this.game = game;
        this.config = config;

        init();
    }

    public void init() {
        System.out.println("App has launched on Html");
        System.out.println("run window");

        game.run();
    }
}

/**
 * Launcher (Client to target)
 */

class DesktopLauncher {
    public void main() {
        DesktopConfig config = new DesktopConfig();
        config.width = Game.WIDTH;
        config.height = Game.HEIGHT;
        config.title = Game.title;

        GameFactory gameFactory = new GameFactory();
        Game game = gameFactory.getGame("Game1");

        new DesktopApplication(game, config);
    }
}

class AndroidLauncher {
    public void onCreate() {
        AndroidConfig config = new AndroidConfig();

        GameFactory gameFactory = new GameFactory();
        Game game = gameFactory.getGame("Game1");

        new AndroidApplication(game, config);
    }
}

class HtmlLauncher {
    public void create() {
        HtmlConfig config = new HtmlConfig();
        config.width = Game.WIDTH;
        config.height = Game.HEIGHT;
        config.title = Game.title;

        GameFactory gameFactory = new GameFactory();
        Game game = gameFactory.getGame("Game1");

        new HtmlApplication(game, config);
    }
}
