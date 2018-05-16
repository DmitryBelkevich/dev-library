package com.hard.states;

import com.hard.GamePanel;
import com.hard.audio.AudioPlayer;
import com.hard.entities.Enemy;
import com.hard.entities.Explosion;
import com.hard.entities.Hud;
import com.hard.entities.Player;
import com.hard.entities.enemies.Slugger;
import com.hard.tiles.Background;
import com.hard.tiles.TileMap;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class Level1State extends GameState {
    private Background background;
    private TileMap tileMap;

    private Player player;

    private List<Enemy> enemies;
    private List<Explosion> explosions;

    private Hud hud;

    private AudioPlayer music;

    public Level1State(GameStateManager gameStateManager) {
        this.gameStateManager = gameStateManager;
        init();
    }

    @Override
    public void init() {
        background = new Background("/backgrounds/grass_bg1.gif", 0.1);

        tileMap = new TileMap(30);
        tileMap.loadTiles("/tilesets/grass_tileset.gif");
        tileMap.loadMap("/maps/level1-1.map");
        tileMap.setPosition(0, 0);
        tileMap.setTween(1);

        player = new Player(tileMap);
        player.setPosition(100, 100);

        populateEnemies();

        explosions = new ArrayList<>();

        hud = new Hud(player);

        music = new AudioPlayer("/music/level1-1.mp3");
        music.play();
    }

    @Override
    public void update() {
        // update player
        player.update();

        // move map
        tileMap.setPosition(GamePanel.WIDTH / 2 - player.getx(), GamePanel.HEIGHT / 2 - player.gety());

        // set background
        background.setPosition(tileMap.getX(), tileMap.getY());

        // attack enemies
        player.checkAttack(enemies);

        // update enemies
        for (int i = 0; i < enemies.size(); i++) {
            Enemy enemy = enemies.get(i);
            enemy.update();

            if (enemy.isDead()) {
                enemies.remove(i);
                i--;
                explosions.add(new Explosion(enemy.getx(), enemy.gety()));
            }
        }

        // update explosions
        for (int i = 0; i < explosions.size(); i++) {
            Explosion explosion = explosions.get(i);
            explosion.update();

            if (explosion.shouldRemove()) {
                explosions.remove(i);
                i--;
            }
        }
    }

    @Override
    public void draw(Graphics2D graphics) {
        // draw background
        background.draw(graphics);

        // draw tilemap
        tileMap.draw(graphics);

        // draw player
        player.draw(graphics);

        // draw enemies
        for (int i = 0; i < enemies.size(); i++) {
            Enemy enemy = enemies.get(i);
            enemy.draw(graphics);
        }

        // draw explosions
        for (int i = 0; i < explosions.size(); i++) {
            Explosion explosion = explosions.get(i);
            explosion.setMapPosition((int) tileMap.getX(), (int) tileMap.getY());
            explosion.draw(graphics);
        }

        // draw hud
        hud.draw(graphics);
    }

    @Override
    public void keyPressed(int key) {
        if (key == KeyEvent.VK_LEFT)
            player.setLeft(true);

        if (key == KeyEvent.VK_RIGHT)
            player.setRight(true);

        if (key == KeyEvent.VK_UP)
            player.setUp(true);

        if (key == KeyEvent.VK_DOWN)
            player.setDown(true);

        if (key == KeyEvent.VK_W)
            player.setJumping(true);

        if (key == KeyEvent.VK_E)
            player.setGliding(true);

        if (key == KeyEvent.VK_R)
            player.setScratching();

        if (key == KeyEvent.VK_F)
            player.setFiring();
    }

    @Override
    public void keyReleased(int key) {
        if (key == KeyEvent.VK_LEFT)
            player.setLeft(false);

        if (key == KeyEvent.VK_RIGHT)
            player.setRight(false);

        if (key == KeyEvent.VK_UP)
            player.setUp(false);

        if (key == KeyEvent.VK_DOWN)
            player.setDown(false);

        if (key == KeyEvent.VK_W)
            player.setJumping(false);

        if (key == KeyEvent.VK_E)
            player.setGliding(false);
    }

    private void populateEnemies() {
        enemies = new ArrayList<>();

        Slugger slugger;

        Point[] points = new Point[]{
                new Point(200, 100),
                new Point(860, 200),
                new Point(1525, 200),
                new Point(1680, 200),
                new Point(1800, 200)
        };

        for (int i = 0; i < points.length; i++) {
            slugger = new Slugger(tileMap);

            Point point = points[i];
            slugger.setPosition(point.x, point.y);

            enemies.add(slugger);
        }
    }
}
