package com.hard.entities;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.hard.animation.Animation;
import com.hard.animation.AnimationManager;
import com.hard.animation.AnimationState;
import com.hard.config.Screen;

public class Entity {
    // state
    private Vector3 position;

    private float w;
    private float h;

    private float dx;
    private float dy;

    private boolean rightKey;
    private boolean leftKey;
    private boolean upKey;
    private boolean downKey;

    // animation
    private Texture texture;
    private AnimationManager animationManager;

    public Entity() {
        position = new Vector3(Screen.WIDTH / 2 - 100 / 2, Screen.HEIGHT / 2 - 100 / 2, 0);

        w = 100;
        h = 100;

        animationManager = new AnimationManager();

        /**
         * init sprite / texture
         */

        texture = new Texture("sprites/player.png");

        /**
         * init animations
         */

        // standing animation

        Animation standingAnimation = new Animation();

        Array<TextureRegion> standingFrames = new Array<>();

        int x_standingFrame = 0;
        int y_standingFrame = 190;
        int w_standingFrame = 135 / 3;
        int h_standingFrame = 48;

        for (int i = 0; i < 3; i++) {
            TextureRegion frame = new TextureRegion();
            frame.setTexture(texture);

            frame.setRegion(x_standingFrame + w_standingFrame * i, y_standingFrame, w_standingFrame, h_standingFrame);

            standingFrames.add(frame);
        }

        standingAnimation.setFrames(standingFrames);
        standingAnimation.setSpeedFrame(0.0);

        // going animation

        Animation goingAnimation = new Animation();

        Array<TextureRegion> goingFrames = new Array<>();

        int x_goingFrame = 0;
        int y_goingFrame = 245;
        int w_goingFrame = 240 / 6;
        int h_goingFrame = 48;

        for (int i = 0; i < 6; i++) {
            TextureRegion frame = new TextureRegion();
            frame.setTexture(texture);

            frame.setRegion(x_goingFrame + w_goingFrame * i, y_goingFrame, w_goingFrame, h_goingFrame);

            goingFrames.add(frame);
        }

        goingAnimation.setFrames(goingFrames);
        goingAnimation.setSpeedFrame(8.0);

        // jumping animation

        Animation jumpingAnimation = new Animation();

        Array<TextureRegion> jumpingFrames = new Array<>();

        int x_jumpingFrame = 0;
        int y_jumpingFrame = 1015;
        int w_jumpingFrame = 150 / 4;
        int h_jumpingFrame = 34;

        for (int i = 0; i < 4; i++) {
            TextureRegion frame = new TextureRegion();
            frame.setTexture(texture);

            frame.setRegion(x_jumpingFrame + w_jumpingFrame * i, y_jumpingFrame, w_jumpingFrame, h_jumpingFrame);

            jumpingFrames.add(frame);
        }

        jumpingAnimation.setFrames(jumpingFrames);
        jumpingAnimation.setSpeedFrame(15.0);

        // sitting animation

        Animation sittingAnimation = new Animation();

        Array<TextureRegion> sittingFrames = new Array<>();

        int x_sittingFrame = 0;
        int y_sittingFrame = 437;
        int w_sittingFrame = 66 / 1;
        int h_sittingFrame = 18;

        for (int i = 0; i < 4; i++) {
            TextureRegion frame = new TextureRegion();
            frame.setTexture(texture);

            frame.setRegion(x_sittingFrame + w_sittingFrame * i, y_sittingFrame, w_sittingFrame, h_sittingFrame);

            sittingFrames.add(frame);
        }

        sittingAnimation.setFrames(sittingFrames);
        sittingAnimation.setSpeedFrame(0.0);
        sittingAnimation.setPlaying(false);

        /**
         * add animations
         */

        animationManager.addAnimation(AnimationState.Entity.STANDING, standingAnimation);
        animationManager.addAnimation(AnimationState.Entity.GOING, goingAnimation);
        animationManager.addAnimation(AnimationState.Entity.JUMPING, jumpingAnimation);
        animationManager.addAnimation(AnimationState.Entity.SITTING, sittingAnimation);

        animationManager.setCurrentAnimation(AnimationState.Entity.STANDING);
    }

    public void handleInput(Input input) {
        if (input.isKeyPressed(Input.Keys.RIGHT)) {
            rightKey = true;
        } else {
            rightKey = false;
        }

        if (input.isKeyPressed(Input.Keys.LEFT)) {
            leftKey = true;
        } else {
            leftKey = false;
        }

        if (input.isKeyPressed(Input.Keys.UP)) {
            upKey = true;
        } else {
            upKey = false;
        }

        if (input.isKeyPressed(Input.Keys.DOWN)) {
            downKey = true;
        } else {
            downKey = false;
        }
    }

    public void update(float time) {
        if (rightKey) {
            dx = 200;

            animationManager.setFlipped(false);
            animationManager.setCurrentAnimation(AnimationState.Entity.GOING);
        }

        if (leftKey) {
            dx = -200;

            animationManager.setFlipped(true);
            animationManager.setCurrentAnimation(AnimationState.Entity.GOING);
        }

        if (rightKey && leftKey) {
            dx = 0;

            animationManager.setCurrentAnimation(AnimationState.Entity.STANDING);
        }

        if (!rightKey && !leftKey) {
            dx = 0;
        }

        if (upKey) {
            dy = 200;

            animationManager.setCurrentAnimation(AnimationState.Entity.GOING);
        }

        if (downKey) {
            dy = -200;

            animationManager.setCurrentAnimation(AnimationState.Entity.GOING);
        }

        if (upKey && downKey) {
            dy = 0;

            animationManager.setCurrentAnimation(AnimationState.Entity.STANDING);
        }

        if (!upKey && !downKey) {
            dy = 0;
        }

        if (!rightKey && !leftKey && !upKey && !downKey) {
            dx = 0;
            dy = 0;

            animationManager.setCurrentAnimation(AnimationState.Entity.STANDING);
        }

        position.x += dx * time;
        position.y += dy * time;

        animationManager.update(time);
    }

    public void draw(SpriteBatch spriteBatch, ShapeRenderer shapeRenderer) {
        animationManager.draw(spriteBatch, position.x, position.y, w, h);
    }

    public void dispose() {
        texture.dispose();
    }
}
