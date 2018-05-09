package com.hard._02_launcher;

public class Main {
    public static void main(String[] args) {
        DesktopLauncher desktopLauncher = new DesktopLauncher();
        desktopLauncher.main();

        AndroidLauncher androidLauncher = new AndroidLauncher();
        androidLauncher.onCreate();

        HtmlLauncher htmlLauncher = new HtmlLauncher();
        htmlLauncher.create();
    }
}

/**
 * Game core
 */

class Game {
    public static final int WIDTH = 480;
    public static final int HEIGHT = 800;
    public static final String title = "Game";

    // image
    // graphic
    // music

    // GameStateManager

    public void run() {

    }
}

/**
 * Config
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
 * Application
 */

class DesktopApplication {
    private Game game;
    private DesktopConfig config;

    public DesktopApplication(Game game, DesktopConfig config) {
        this.game = game;
        this.config = config;

        System.out.println("App has launched on Desktop");
    }
}

class AndroidApplication {
    private Game game;
    private AndroidConfig config;

    public AndroidApplication(Game game, AndroidConfig config) {
        this.game = game;
        this.config = config;

        System.out.println("App has launched on Android");
    }
}

class HtmlApplication {
    private Game game;
    private HtmlConfig config;

    public HtmlApplication(Game game, HtmlConfig config) {
        this.game = game;
        this.config = config;

        System.out.println("App has launched on Html");
    }
}

/**
 * Launcher
 */

class DesktopLauncher {
    public void main() {
        DesktopConfig config = new DesktopConfig();
        config.width = Game.WIDTH;
        config.height = Game.HEIGHT;
        config.title = Game.title;

        Game game = new Game();

        new DesktopApplication(game, config);
    }
}

class AndroidLauncher {
    public void onCreate() {
        AndroidConfig config = new AndroidConfig();

        Game game = new Game();

        new AndroidApplication(game, config);
    }
}

class HtmlLauncher {
    public void create() {
        HtmlConfig config = new HtmlConfig();
        config.width = Game.WIDTH;
        config.height = Game.HEIGHT;
        config.title = Game.title;

        Game game = new Game();

        new HtmlApplication(game, config);
    }
}
