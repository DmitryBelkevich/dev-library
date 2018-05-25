package com.hard.entities;

import com.hard.Camera;
import com.hard.Game;
import com.hard.Tile;
import com.hard.animation.Animation;
import com.hard.animation.AnimationManager;
import com.hard.animation.AnimationState;
import com.hard.config.Screen;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class Entity {
    private int w = 32 * 2;
    private int h = 32 * 2;

    private double x = Screen.WIDTH / 2 - w / 2;
    private double y = Screen.HEIGHT / 2 - h / 2;

    private double speed;

    private double dx;
    private double dy;

    // states
    private boolean left;
    private boolean right;
    private boolean jumping;
    private boolean sitting;

    // collision
    private boolean leftCollision;
    private boolean rightCollision;
    private boolean topCollision;
    private boolean bottomCollision;

    // animation
    private AnimationManager animationManager;

    // console
    private Console console;

    public Entity() {
        animationManager = new AnimationManager();

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

        /**
         * init animations
         */

        // standing animation

        Animation standingAnimation = new Animation();

        List<BufferedImage> standingFrames = new ArrayList<>();

        int x_standingFrame = 0;
        int y_standingFrame = 190;
        int w_standingFrame = 135 / 3;
        int h_standingFrame = 48;

        for (int i = 0; i < 3; i++) {
            BufferedImage frame = sprite.getSubimage(x_standingFrame + w_standingFrame * i, y_standingFrame, w_standingFrame, h_standingFrame);
            standingFrames.add(frame);
        }

        standingAnimation.setFrames(standingFrames);
        standingAnimation.setSpeedFrame(0.0800);

        // going animation

        Animation goingAnimation = new Animation();

        List<BufferedImage> goingFrames = new ArrayList<>();

        int x_goingFrame = 0;
        int y_goingFrame = 245;
        int w_goingFrame = 240 / 6;
        int h_goingFrame = 48;

        for (int i = 0; i < 6; i++) {
            BufferedImage frame = sprite.getSubimage(x_goingFrame + w_goingFrame * i, y_goingFrame, w_goingFrame, h_goingFrame);
            goingFrames.add(frame);
        }

        goingAnimation.setFrames(goingFrames);
        goingAnimation.setSpeedFrame(0.1000);

        // jumping animation

        Animation jumpingAnimation = new Animation();

        List<BufferedImage> jumpingFrames = new ArrayList<>();

        int x_jumpingFrame = 0;
        int y_jumpingFrame = 1015;
        int w_jumpingFrame = 150 / 4;
        int h_jumpingFrame = 34;

        for (int i = 0; i < 4; i++) {
            BufferedImage frame = sprite.getSubimage(x_jumpingFrame + w_jumpingFrame * i, y_jumpingFrame, w_jumpingFrame, h_jumpingFrame);
            jumpingFrames.add(frame);
        }

        jumpingAnimation.setFrames(jumpingFrames);
        jumpingAnimation.setSpeedFrame(0.1000);

        // sitting animation

        Animation sittingAnimation = new Animation();

        List<BufferedImage> sittingFrames = new ArrayList<>();

        int x_sittingFrame = 0;
        int y_sittingFrame = 437;
        int w_sittingFrame = 66 / 1;
        int h_sittingFrame = 18;

        for (int i = 0; i < 4; i++) {
            BufferedImage frame = sprite.getSubimage(x_sittingFrame + w_sittingFrame * i, y_sittingFrame, w_sittingFrame, h_sittingFrame);
            sittingFrames.add(frame);
        }

        sittingAnimation.setFrames(sittingFrames);
        sittingAnimation.setSpeedFrame(0.0000);
        sittingAnimation.setPlaying(false);

        /**
         * add animations
         */

        animationManager.addAnimation(AnimationState.Entity.STANDING, standingAnimation);
        animationManager.addAnimation(AnimationState.Entity.GOING, goingAnimation);
        animationManager.addAnimation(AnimationState.Entity.JUMPING, jumpingAnimation);
        animationManager.addAnimation(AnimationState.Entity.SITTING, sittingAnimation);

        animationManager.setCurrentAnimation(AnimationState.Entity.STANDING);

        /**
         * console
         */
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

    public boolean isJumping() {
        return jumping;
    }

    public void setJumping(boolean jumping) {
        this.jumping = jumping;
    }

    public boolean isSitting() {
        return sitting;
    }

    public void setSitting(boolean sitting) {
        this.sitting = sitting;
    }

    public boolean isLeftCollision() {
        return leftCollision;
    }

    public void setLeftCollision(boolean leftCollision) {
        this.leftCollision = leftCollision;
    }

    public boolean isRightCollision() {
        return rightCollision;
    }

    public void setRightCollision(boolean rightCollision) {
        this.rightCollision = rightCollision;
    }

    public boolean isTopCollision() {
        return topCollision;
    }

    public void setTopCollision(boolean topCollision) {
        this.topCollision = topCollision;
    }

    public boolean isBottomCollision() {
        return bottomCollision;
    }

    public void setBottomCollision(boolean bottomCollision) {
        this.bottomCollision = bottomCollision;
    }

    public void update(double time) {
        /**
         * check states
         */

        if (left) {
            speed = 5;
            dx = -speed;

            animationManager.setFlipped(true);
            if (bottomCollision) {
                animationManager.setCurrentAnimation(AnimationState.Entity.GOING);
            }
        }

        if (right) {
            speed = 5;
            dx = speed;

            animationManager.setFlipped(false);
            if (bottomCollision) {
                animationManager.setCurrentAnimation(AnimationState.Entity.GOING);
            }
        }

        if (jumping) {
            if (bottomCollision) {
                dy = -24.0;
                bottomCollision = false;

                animationManager.setCurrentAnimation(AnimationState.Entity.JUMPING);
            }
        }

        if (sitting) {
            if (bottomCollision) {
                animationManager.setCurrentAnimation(AnimationState.Entity.SITTING);
            }
        }

        if (!left && !right) {
            speed = 0;
            dx = speed;
        }

        if (!left && !right && !jumping && !sitting) {
            if (bottomCollision) {
                animationManager.setCurrentAnimation(AnimationState.Entity.STANDING);
            }
        }

        /**
         * speed-up
         */

        if (!bottomCollision)
            dy += Game.GRAVITY * time;

        bottomCollision = false;

        /**
         * moving
         * check collisions with map
         */

        x += dx * time;
        checkCollision('x');
        y += dy * time;
        checkCollision('y');

        /**
         * check collision with global
         */

//        if (x < 0)
//            x = 0;
//
//        if (y < 0)
//            y = 0;
//
//        if (x > Game.WIDTH - w)
//            x = Game.WIDTH - w;
//
//        if (y > Game.HEIGHT - h)
//            y = Game.HEIGHT - h;

        // check collision 2

//        if (y >= Game.HEIGHT - h) {
//            bottomCollision = true;
//            dy = 0;
//        }

        /**
         * camera moving
         */

        Camera.x = x - Screen.WIDTH / 2 + (w / 2);
        Camera.y = y - Screen.HEIGHT / 2 + (h / 2);

        /**
         * hide behind-the-scenes
         */

        if (Camera.x < 0)
            Camera.x = 0;

        if (Camera.x > Tile.W * TileMap.W - Screen.WIDTH)
            Camera.x = Tile.W * TileMap.W - Screen.WIDTH;

        if (Camera.y < 0)
            Camera.y = 0;

        if (Camera.y > Tile.H * TileMap.H - Screen.HEIGHT)
            Camera.y = Tile.H * TileMap.H - Screen.HEIGHT;

        /**
         * stop moving
         */

        /**
         * animation
         */
        animationManager.update(time);

//        w = animationManager.getWidth();
//        h = animationManager.getHeight();
    }

    public void draw(Graphics graphics) {
        // drawing background
        graphics.setColor(new Color(255, 0, 0, 255));
        graphics.fillRect((int) (x - Camera.x), (int) (y - Camera.y), w, h);

        // drawing entity
        animationManager.draw(graphics, x - Camera.x, y - Camera.y, w, h);

        console.draw(graphics, this);
    }

    private void checkCollision(char direction) {
        int rowFirst = (int) (y / Tile.H);
        double rowLast = (y + h) / Tile.H;

        for (int i = rowFirst; i < rowLast; i++) {
            int columnFirst = (int) (x / Tile.W);
            double columnLast = (x + w) / Tile.W;

            for (int j = columnFirst; j < columnLast; j++) {
                String row = TileMap.tilemap[i];
                char tile = row.charAt(j);

                if (tile == 'B') {
                    if ((dx > 0) && (direction == 'x'))
                        x = j * Tile.W - w;

                    if ((dx < 0) && (direction == 'x'))
                        x = j * Tile.W + Tile.W;

                    if ((dy > 0) && (direction == 'y')) {
                        y = i * Tile.H - h;
                        dy = 0;
                        bottomCollision = true;
                    }

                    if ((dy < 0) && (direction == 'y')) {
                        y = i * Tile.H + Tile.H;
                        dy = 0;
                    }
                }

                if (tile == '0') {
                    char newTile = ' ';

                    String oldRow = TileMap.tilemap[i];
                    String newRow = oldRow.substring(0, j) + newTile + oldRow.substring(j + 1);

                    TileMap.tilemap[i] = newRow;
                }
            }
        }
    }
}
