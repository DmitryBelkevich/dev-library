package com.hard.entities;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;
import com.hard.config.Screen;

public class Bird {
    // state
    private Vector3 position;

    private float elapsedTime;

    // animation
    private Texture texture;
    private Animation animation;
    private TextureRegion currentFrame;

    public Bird() {
        position = new Vector3(Screen.WIDTH / 2 - 100 / 2 + 100, Screen.HEIGHT / 2 - 100 / 2, 0);

        /**
         * init sprite / texture
         */

        texture = new Texture("sprites/bird.png");

        /**
         * init animations
         */

        int columns = 3;
        int rows = 1;

        int w = 102;
        int h = 24;

        TextureRegion[][] framesTemp = TextureRegion.split(texture, w / columns, h / rows);
        TextureRegion[] frames = new TextureRegion[columns * rows];

        int index = 0;
        for (int i = 0; i < columns; i++) {
            for (int j = 0; j < rows; j++) {
                frames[index++] = framesTemp[j][i];
            }
        }

        animation = new Animation(0.25f, frames);
    }

    public void handleInput(Input input) {

    }

    public void update(float time) {
        elapsedTime += time;
        currentFrame = (TextureRegion) animation.getKeyFrame(elapsedTime, true);
    }

    public void draw(SpriteBatch spriteBatch, ShapeRenderer shapeRenderer) {
        spriteBatch.begin();
        spriteBatch.draw(currentFrame, position.x, position.y);
        spriteBatch.end();
    }

    public void dispose() {
        texture.dispose();
    }
}
