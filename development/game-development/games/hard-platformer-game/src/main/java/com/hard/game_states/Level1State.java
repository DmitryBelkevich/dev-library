package com.hard.game_states;

import com.hard.entities.Camera;
import com.hard.entities.Console;
import com.hard.entities.Entity;
import com.hard.entities.TileMap;
import com.hard.maps.Maps;
import com.hard.utils.MapLoader;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class Level1State implements GameState {
    private TileMap tileMap;
    private Entity entity;
    private Camera camera;
    private List<Console> consoles;

    public Level1State() {
        /**
         * tile map
         */
        tileMap = new TileMap();

        MapLoader mapLoader = new MapLoader();

        char[][] chars = mapLoader.loadStringArray(Maps.stage1.level1);

        tileMap.setW(mapLoader.getW());
        tileMap.setH(mapLoader.getH());

        tileMap.setChars(chars);

        /**
         * entity
         */

        entity = new Entity();
        entity.setTileMap(tileMap);

        /**
         * camera
         */

        camera = new Camera();

        /**
         * console
         */

        consoles = new ArrayList<>();

        Console entityConsole = new Console();
        entityConsole.setX(0);
        entityConsole.setY(10);
        entityConsole.setEntity(entity);

        Console cameraConsole = new Console();
        cameraConsole.setX(200);
        cameraConsole.setY(10);
        cameraConsole.setEntity(camera);

        consoles.add(entityConsole);
        consoles.add(cameraConsole);
    }

    @Override
    public void update(double time) {
        tileMap.update(time);
        entity.update(time);
        camera.update(time);

        for (int i = 0; i < consoles.size(); i++) {
            Console console = consoles.get(i);
            console.update(time);
        }
    }

    @Override
    public void draw(Graphics graphics) {
        tileMap.draw(graphics);
        entity.draw(graphics);
        camera.draw(graphics);

        for (int i = 0; i < consoles.size(); i++) {
            Console console = consoles.get(i);
            console.draw(graphics);
        }
    }

    /**
     * KeyListener
     */

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();

        /**
         * entity
         */

        if (keyCode == KeyEvent.VK_LEFT)
            entity.setLeft(true);

        if (keyCode == KeyEvent.VK_RIGHT)
            entity.setRight(true);

        if (keyCode == KeyEvent.VK_UP)
            entity.setJumping(true);

        if (keyCode == KeyEvent.VK_DOWN)
            entity.setSitting(true);

        /**
         * camera
         */

        int step = 5;

        if (keyCode == KeyEvent.VK_A) {
            camera.setX(camera.getX() - step);
        }

        if (keyCode == KeyEvent.VK_D) {
            camera.setX(camera.getX() + step);
        }

        if (keyCode == KeyEvent.VK_W) {
            camera.setY(camera.getY() - step);
        }

        if (keyCode == KeyEvent.VK_S) {
            camera.setY(camera.getY() + step);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();

        /**
         * entity
         */

        if (keyCode == KeyEvent.VK_LEFT)
            entity.setLeft(false);

        if (keyCode == KeyEvent.VK_RIGHT)
            entity.setRight(false);

        if (keyCode == KeyEvent.VK_UP)
            entity.setJumping(false);

        if (keyCode == KeyEvent.VK_DOWN)
            entity.setSitting(false);
    }
}
