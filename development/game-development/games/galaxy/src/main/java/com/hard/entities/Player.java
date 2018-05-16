package com.hard.entities;

import com.hard.GamePanel;

import java.awt.*;

public class Player {
    private double x;
    private double y;
    private int r;

    private int dx;
    private int dy;
    private int speed;

    private boolean left;
    private boolean right;
    private boolean up;
    private boolean down;

    private boolean firing;
    private long firingTimer;
    private long firingDelay;

    private boolean recovering;
    private long recoveryTimer;

    private int lives;
    private Color color1;
    private Color color2;

    private int score;

    private int powerLevel;
    private int power;
    private int[] requiredPower = {
            1, 2, 3, 4, 5
    };

    public Player() {
        x = GamePanel.WIDTH / 2;
        y = GamePanel.HEIGHT / 2;
        r = 5;

        dx = 0;
        dy = 0;
        speed = 5;

        lives = 3;
        color1 = Color.WHITE;
        color2 = Color.RED;

        firing = false;
        firingTimer = System.nanoTime();
        firingDelay = 200;

        recovering = false;
        recoveryTimer = 0;

        score = 0;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public int getR() {
        return r;
    }

    public int getScore() {
        return score;
    }

    public int getLives() {
        return lives;
    }

    public boolean isDead() {
        return lives <= 0;
    }

    public boolean isRecovering() {
        return recovering;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    public void setDown(boolean down) {
        this.down = down;
    }

    public void setFiring(boolean firing) {
        this.firing = firing;
    }

    public void addScore(int i) {
        score += i;
    }

    public void gainLife() {
        lives++;
    }

    public void loseLife() {
        lives--;
        recovering = true;
        recoveryTimer = System.nanoTime();
    }

    public void increasePower(int i) {
        power += i;

        if (powerLevel == 4) {
            if (power > requiredPower[powerLevel])
                power = requiredPower[powerLevel];

            return;
        }

        if (power >= requiredPower[powerLevel]) {
            power -= requiredPower[powerLevel];
            powerLevel++;
        }
    }

    public int getPowerLevel() {
        return powerLevel;
    }

    public int getPower() {
        return power;
    }

    public int getRequiredPower() {
        return requiredPower[powerLevel];
    }

    public void update() {
        if (left)
            dx = -speed;

        if (right)
            dx = speed;

        if (up)
            dy = -speed;

        if (down)
            dy = speed;

        x += dx;
        y += dy;

        if (x < r)
            x = r;

        if (y < r)
            y = r;

        if (x > GamePanel.WIDTH - r)
            x = GamePanel.WIDTH - r;

        if (y > GamePanel.HEIGHT - r)
            y = GamePanel.HEIGHT - r;

        dx = 0;
        dy = 0;

        // firing
        if (firing) {
            long elapsed = (System.nanoTime() - firingTimer) / 1000000;

            if (elapsed > firingDelay) {

                firingTimer = System.nanoTime();

                if (powerLevel < 2) {
                    GamePanel.bullets.add(new Bullet(x, y, 270));
                } else if (powerLevel < 4) {
                    GamePanel.bullets.add(new Bullet(x + 5, y, 270));
                    GamePanel.bullets.add(new Bullet(x - 5, y, 270));
                } else {
                    GamePanel.bullets.add(new Bullet(x, y, 270));
                    GamePanel.bullets.add(new Bullet(x + 5, y, 275));
                    GamePanel.bullets.add(new Bullet(x - 5, y, 265));
                }
            }
        }

        if (recovering) {
            long elapsed = (System.nanoTime() - recoveryTimer) / 1000000;

            if (elapsed > 2000) {
                recovering = false;
                recoveryTimer = 0;
            }
        }
    }

    public void draw(Graphics2D graphics) {
        if (recovering) {
            graphics.setColor(color2);
            graphics.fillOval((int) (x - r), (int) (y - r), 2 * r, 2 * r);

            graphics.setStroke(new BasicStroke(3));
            graphics.setColor(color2.darker());
            graphics.drawOval((int) (x - r), (int) (y - r), 2 * r, 2 * r);
            graphics.setStroke(new BasicStroke(1));
        } else {
            graphics.setColor(color1);
            graphics.fillOval((int) (x - r), (int) (y - r), 2 * r, 2 * r);

            graphics.setStroke(new BasicStroke(3));
            graphics.setColor(color1.darker());
            graphics.drawOval((int) (x - r), (int) (y - r), 2 * r, 2 * r);
            graphics.setStroke(new BasicStroke(1));
        }
    }
}
