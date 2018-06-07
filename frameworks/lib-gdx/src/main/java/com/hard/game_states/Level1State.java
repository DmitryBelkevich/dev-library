package com.hard.game_states;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.hard.entities.Bird;
import com.hard.entities.Entity;
import com.hard.entities.TileMap;

public class Level1State extends GameState {
    // entities
    private TileMap tileMap;
    private Entity entity;
    private Bird bird;

    public Level1State(GameStateManager gameStateManager) {
        super(gameStateManager);

        // entities
        tileMap = new TileMap();
        entity = new Entity();
        bird = new Bird();
    }

    @Override
    public void handleInput(Input input) {
        tileMap.handleInput(input);
        entity.handleInput(input);
        bird.handleInput(input);

        /**
         * Camera
         */

        if (input.isKeyPressed(Input.Keys.A)) {
            camera.position.x--;
        }

        if (input.isKeyPressed(Input.Keys.D)) {
            camera.position.x++;
        }

        if (input.isKeyPressed(Input.Keys.W)) {
            camera.position.y++;
        }

        if (input.isKeyPressed(Input.Keys.S)) {
            camera.position.y--;
        }

        if (input.isKeyPressed(Input.Keys.Z) && !input.isKeyPressed(Input.Keys.SHIFT_LEFT)) {
            camera.zoom -= 0.001;
        }

        if (input.isKeyPressed(Input.Keys.Z) && input.isKeyPressed(Input.Keys.SHIFT_LEFT)) {
            camera.zoom += 0.001;
        }

        if (input.isKeyPressed(Input.Keys.R) && !input.isKeyPressed(Input.Keys.SHIFT_LEFT)) {
            camera.rotate((float) Math.toRadians(-100));
        }

        if (input.isKeyPressed(Input.Keys.R) && input.isKeyPressed(Input.Keys.SHIFT_LEFT)) {
            camera.rotate((float) Math.toRadians(100));
        }
    }

    @Override
    public void update(float time) {
        tileMap.update(time);
        entity.update(time);
        bird.update(time);

        camera.update();
    }

    @Override
    public void draw(SpriteBatch spriteBatch, ShapeRenderer shapeRenderer) {
        tileMap.draw(spriteBatch, shapeRenderer);
        entity.draw(spriteBatch, shapeRenderer);
        bird.draw(spriteBatch, shapeRenderer);

        spriteBatch.setProjectionMatrix(camera.combined);
        shapeRenderer.setProjectionMatrix(camera.combined);
    }

    @Override
    public void dispose() {
        tileMap.dispose();
        entity.dispose();
        bird.dispose();
    }
}
