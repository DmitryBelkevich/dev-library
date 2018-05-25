package com.hard.game_states;

import com.hard.entities.Entity;
import com.hard.entities.TileMap;
import com.hard.maps.Maps;
import com.hard.utils.MapLoader;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Level1State implements GameState {
    // tilemap
    private TileMap tileMap;

    // entities
    private Entity entity;

    public Level1State() {
        /**
         * tile map
         */
        tileMap = new TileMap();

        MapLoader mapLoader = new MapLoader();

        char[][] charsTilemap = mapLoader.loadStringArray(Maps.stage1.level1);

        tileMap.setW(mapLoader.getW());
        tileMap.setH(mapLoader.getH());

        tileMap.setCharsTilemap(charsTilemap);

        /**
         * entity
         */

        entity = new Entity();
        entity.setTileMap(tileMap);
    }

    @Override
    public void update(double time) {
        tileMap.update(time);

        entity.update(time);
    }

    @Override
    public void draw(Graphics graphics) {
        tileMap.draw(graphics);

        entity.draw(graphics);
    }

    /**
     * KeyListener
     */

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();

        if (keyCode == KeyEvent.VK_LEFT)
            entity.setLeft(true);

        if (keyCode == KeyEvent.VK_RIGHT)
            entity.setRight(true);

        if (keyCode == KeyEvent.VK_UP)
            entity.setJumping(true);

        if (keyCode == KeyEvent.VK_DOWN)
            entity.setSitting(true);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();

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
