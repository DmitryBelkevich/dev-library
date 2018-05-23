package com.hard.entities;

import com.hard.Game;
import com.hard.animation.Animation;
import com.hard.animation.AnimationManager;
import com.hard.animation.AnimationState;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class Entity {
    private int w = 64;
    private int h = 64;

    private double x = Game.WIDTH / 2 - w / 2;
    private double y = Game.HEIGHT / 2 - h / 2;

    private double speed;

    private double dx;
    private double dy;

    // moving
    private boolean left;
    private boolean right;
    private boolean up;
    private boolean down;

    // animation
    private AnimationManager animationManager;

    // console
    private Console console;

    public Entity() {
        animationManager = new AnimationManager();

        Animation standAnimation = new Animation();
        Animation walkAnimation = new Animation();

        /**
         * init sprite
         */

        Class<?> clazz = this.getClass();
        InputStream inputStream = clazz.getResourceAsStream("/sprites/player.png");

        BufferedImage sprite = null;
        try {
            sprite = ImageIO.read(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // stand frames

        List<BufferedImage> standFrames = new ArrayList<>();

        int x_standFrame = 0;
        int y_standFrame = 190;
        int w_standFrame = 45;
        int h_standFrame = 48;

        for (int i = 0; i < 3; i++) {
            BufferedImage frame = sprite.getSubimage(x_standFrame + w_standFrame * i, y_standFrame, w_standFrame, h_standFrame);
            standFrames.add(frame);
        }

        standAnimation.setFrames(standFrames);
        standAnimation.setSpeedFrame(0.0800);

        // walk frames

        List<BufferedImage> walkFrames = new ArrayList<>();

        int x_walkFrame = 0;
        int y_walkFrame = 245;
        int w_walkFrame = 40;
        int h_walkFrame = 48;

        for (int i = 0; i < 6; i++) {
            BufferedImage frame = sprite.getSubimage(x_walkFrame + w_walkFrame * i, y_walkFrame, w_walkFrame, h_walkFrame);
            walkFrames.add(frame);
        }

        walkAnimation.setFrames(walkFrames);
        walkAnimation.setSpeedFrame(0.1000);

        /**
         * add animations
         */

        animationManager.addAnimation(AnimationState.Entity.STAND, standAnimation);
        animationManager.addAnimation(AnimationState.Entity.WALK, walkAnimation);

        animationManager.setCurrentAnimation(AnimationState.Entity.STAND);

        // console
        console = new Console();
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public double getDx() {
        return dx;
    }

    public void setDx(double dx) {
        this.dx = dx;
    }

    public double getDy() {
        return dy;
    }

    public void setDy(double dy) {
        this.dy = dy;
    }

    public boolean isLeft() {
        return left;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public boolean isRight() {
        return right;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public boolean isUp() {
        return up;
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    public boolean isDown() {
        return down;
    }

    public void setDown(boolean down) {
        this.down = down;
    }

    public void update(double time) {
        /**
         * check moving
         */

        if (left) {
            speed = 5;
            dx = -speed;

            animationManager.setFlipped(true);
            animationManager.setCurrentAnimation(AnimationState.Entity.WALK);
        }

        if (right) {
            speed = 5;
            dx = speed;

            animationManager.setFlipped(false);
            animationManager.setCurrentAnimation(AnimationState.Entity.WALK);
        }

        if (up) {
            speed = 5;
            dy = -speed;

            animationManager.setCurrentAnimation(AnimationState.Entity.WALK);
        }

        if (down) {
            speed = 5;
            dy = speed;

            animationManager.setCurrentAnimation(AnimationState.Entity.WALK);
        }

        if (!left && !right) {
            speed = 0;
            dx = speed;
        }

        if (!up && !down) {
            speed = 0;
            dy = speed;
        }

        if (!left && !right && !up && !down) {
            animationManager.setCurrentAnimation(AnimationState.Entity.STAND);
        }

        /**
         * moving
         */

        x += dx * time;
        y += dy * time;

        /**
         * check collision
         */

        if (x < 0)
            x = 0;

        if (y < 0)
            y = 0;

        if (x > Game.WIDTH - w)
            x = Game.WIDTH - w;

        if (y > Game.HEIGHT - h)
            y = Game.HEIGHT - h;

        /**
         * stop moving
         */

        /**
         * animation
         */
        animationManager.update(time);
    }

    public void draw(Graphics graphics) {
        // drawing background
        graphics.setColor(new Color(255, 0, 0, 255));
        graphics.fillRect((int) x, (int) y, w, h);

        // drawing entity
        animationManager.draw(graphics, x, y, w, h);

        console.draw(graphics, this);
    }
}
