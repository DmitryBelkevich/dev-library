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

    private double speed = 0;

    private double dx = 0;
    private double dy = 0;

    // moving
    private boolean left = false;
    private boolean right = false;
    private boolean up = false;
    private boolean down = false;

    // animation
    private AnimationManager animationManager;

    private Console console;

    public Entity() {
        animationManager = new AnimationManager();

        Animation standAnimation = new Animation();
        Animation walkAnimation = new Animation();

        /**
         * init sprite
         */

        Class<?> clazz = this.getClass();
        InputStream inputStream = clazz.getResourceAsStream("/player.png");

        BufferedImage sprite = null;
        try {
            sprite = ImageIO.read(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // stand frames

        List<BufferedImage> standFrames = new ArrayList<>();

        int x_standFrame = 0;
        int y_standFrame = 220;
        int w_standFrame = 95;
        int h_standFrame = 75;

        BufferedImage standFrame1 = sprite.getSubimage(x_standFrame + w_standFrame * 1, y_standFrame, w_standFrame, h_standFrame);

        standFrames.add(standFrame1);

        standAnimation.setFrames(standFrames);
        standAnimation.setSpeedFrame(0.145);

        // walk frames

        List<BufferedImage> walkFrames = new ArrayList<>();

        int x_walkFrame = 0;
        int y_walkFrame = 220;
        int w_walkFrame = 95;
        int h_walkFrame = 75;

        BufferedImage walkFrame1 = sprite.getSubimage(x_walkFrame + w_walkFrame * 0, y_walkFrame, w_walkFrame, h_walkFrame);
        BufferedImage walkFrame2 = sprite.getSubimage(x_walkFrame + w_walkFrame * 1, y_walkFrame, w_walkFrame, h_walkFrame);
        BufferedImage walkFrame3 = sprite.getSubimage(x_walkFrame + w_walkFrame * 2, y_walkFrame, w_walkFrame, h_walkFrame);

        walkFrames.add(walkFrame1);
        walkFrames.add(walkFrame2);
        walkFrames.add(walkFrame3);

        walkAnimation.setFrames(walkFrames);
        walkAnimation.setSpeedFrame(0.145);

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

        speed = 0;
        dx = speed;
    }

    public boolean isRight() {
        return right;
    }

    public void setRight(boolean right) {
        this.right = right;

        speed = 0;
        dx = speed;
    }

    public boolean isUp() {
        return up;
    }

    public void setUp(boolean up) {
        this.up = up;

        speed = 0;
        dy = speed;
    }

    public boolean isDown() {
        return down;
    }

    public void setDown(boolean down) {
        this.down = down;

        speed = 0;
        dy = speed;
    }

    public void update(double time) {
        /**
         * check moving
         */

        if (left) {
            moveToLeft();
            animationManager.setFlipped(true);
            animationManager.setCurrentAnimation(AnimationState.Entity.WALK);
        }

        if (right) {
            moveToRight();
            animationManager.setFlipped(false);
            animationManager.setCurrentAnimation(AnimationState.Entity.WALK);
        }

        if (up) {
            moveToUp();
            animationManager.setCurrentAnimation(AnimationState.Entity.WALK);
        }

        if (down) {
            moveToDown();
            animationManager.setCurrentAnimation(AnimationState.Entity.WALK);
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
        // drawing entity (circle)
        graphics.setColor(new Color(255, 0, 0, 255));
        graphics.fillOval((int) x, (int) y, w, h);

        // drawing entity (sprite)
        animationManager.draw(graphics, x, y, w, h);

        console.draw(graphics, this);
    }

    public void moveToLeft() {
        speed = 5;
        dx = -speed;
    }

    public void moveToRight() {
        speed = 5;
        dx = speed;
    }

    public void moveToUp() {
        speed = 5;
        dy = -speed;
    }

    public void moveToDown() {
        speed = 5;
        dy = speed;
    }
}
