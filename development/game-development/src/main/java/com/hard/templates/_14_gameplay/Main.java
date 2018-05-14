package com.hard.templates._14_gameplay;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        JFrame window = new JFrame("platformer, arcada game");

        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);

        window.setContentPane(new GamePanel());

        window.pack();
        window.setVisible(true);
    }
}

class GamePanel extends JPanel implements Runnable, KeyListener {
    public static final int WIDTH = 320;
    public static final int HEIGHT = 240;
    public static final int SCALE = 1;

    private Thread thread;
    private boolean isRunning;

    private BufferedImage image;
    private Graphics2D graphics;

    private int fps = 30;
    private double averageFps;

    private long targetTime = 1000 / fps;

    // entities
    private static Player player;
    public static List<Bullet> bullets;
    public static List<Enemy> enemies;
    public static List<PowerUp> powerUps;
    public static List<Explosion> explosions;
    public static List<Text> texts;

    private long waveStartTimer;
    private long waveStartTimerDiff;
    private int waveNumber;
    private boolean waveStart;
    private int waveDelay = 2000;

    private long slowDownTimer;
    private long slowDownTimerDiff;
    private int slowDownLength = 6000;

    public GamePanel() {
        super();
        setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
        setFocusable(true);
        requestFocus();
    }

    @Override
    public void addNotify() {
        super.addNotify();

        if (thread == null) {
            thread = new Thread(this);

            thread.start();
        }

        addKeyListener(this);
    }

    @Override
    public void run() {
        isRunning = true;

        image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        graphics = (Graphics2D) image.getGraphics();

        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        player = new Player();
        bullets = new ArrayList<>();
        enemies = new ArrayList<>();
        powerUps = new ArrayList<>();
        explosions = new ArrayList<>();
        texts = new ArrayList<>();

        waveStartTimer = 0;
        waveStartTimerDiff = 0;
        waveNumber = 0;
        waveStart = true;

        long totalTime = 0;

        int frameCount = 0;
        int maxFrameCount = 30;

        // game loop
        while (isRunning) {
            long startTime = System.nanoTime();

            gameUpdate();
            gameRender();
            gameDraw();

            long urdTimeMills = (System.nanoTime() - startTime) / 1000000;

            long waitingTime = targetTime - urdTimeMills;
            if (waitingTime < 0)
                waitingTime = 0;

            try {
                Thread.sleep(waitingTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            totalTime += System.nanoTime() - startTime;
            frameCount++;

            if (frameCount == maxFrameCount) {
                averageFps = 1000.0 / ((totalTime / frameCount) / 1000000);
                frameCount = 0;
                totalTime = 0;
            }
        }

        graphics.setColor(new Color(0, 100, 255));
        graphics.fillRect(0, 0, WIDTH, HEIGHT);
        graphics.setColor(Color.WHITE);
        graphics.setFont(new Font("Century Gothic", Font.PLAIN, 16));

        String str = "G A M E   O V E R";
        FontMetrics fontMetrics = graphics.getFontMetrics();
        Rectangle2D rectangle = fontMetrics.getStringBounds(str, graphics);
        int length = (int) rectangle.getWidth();
        graphics.drawString(str, (WIDTH - length) / 2, HEIGHT / 2);

        String str2 = "Final score: " + player.getScore();
        FontMetrics fontMetrics2 = graphics.getFontMetrics();
        Rectangle2D rectangle2 = fontMetrics2.getStringBounds(str2, graphics);
        int length2 = (int) rectangle2.getWidth();
        graphics.drawString(str2, (WIDTH - length2) / 2, HEIGHT / 2 + 30);

        gameDraw();
    }

    private void gameUpdate() {
        // new wave
        if (waveStartTimer == 0 && enemies.size() == 0) {
            waveStartTimer = System.nanoTime();
            waveNumber++;
            waveStart = false;
        } else {
            waveStartTimerDiff = (System.nanoTime() - waveStartTimer) / 1000000;

            if (waveStartTimerDiff > waveDelay) {
                waveStartTimer = 0;
                waveStartTimerDiff = 0;
                waveStart = true;
            }
        }

        // create enemies
        if (waveStart && enemies.size() == 0)
            createNewEnemies();

        // player update
        player.update();

        // bullet update
        for (int i = 0; i < bullets.size(); i++) {
            Bullet bullet = bullets.get(i);
            boolean remove = bullet.update();

            if (remove) {
                bullets.remove(i);
                i--;
            }
        }

        // powerUp update
        for (int i = 0; i < powerUps.size(); i++) {
            PowerUp powerUp = powerUps.get(i);
            boolean remove = powerUp.update();

            if (remove) {
                powerUps.remove(i);
                i--;
            }
        }

        // explosion update
        for (int i = 0; i < explosions.size(); i++) {
            Explosion explosion = explosions.get(i);
            boolean remove = explosion.update();

            if (remove) {
                explosions.remove(i);
                i--;
            }
        }

        // text update
        for (int i = 0; i < texts.size(); i++) {
            Text text = texts.get(i);
            boolean remove = text.update();

            if (remove) {
                texts.remove(i);
                i--;
            }
        }

        // enemy update
        for (int i = 0; i < enemies.size(); i++) {
            Enemy enemy = enemies.get(i);
            enemy.update();
        }

        // bullet-enemy collision
        for (int i = 0; i < bullets.size(); i++) {
            Bullet bullet = bullets.get(i);

            double bulletX = bullet.getX();
            double bulletY = bullet.getY();
            double bulletR = bullet.getR();

            for (int j = 0; j < enemies.size(); j++) {
                Enemy enemy = enemies.get(j);

                double enemyX = enemy.getX();
                double enemyY = enemy.getY();
                double enemyR = enemy.getR();

                double dx = bulletX - enemyX;
                double dy = bulletY - enemyY;

                double distance = Math.sqrt(dx * dx + dy * dy);

                if (distance < bulletR + enemyR) {
                    enemy.hit();
                    bullets.remove(i);
                    i--;
                    break;
                }
            }
        }

        // check dead enemies
        for (int i = 0; i < enemies.size(); i++) {
            Enemy enemy = enemies.get(i);

            if (enemy.isDead()) {
                // chance for powerUp
                double random = Math.random();

                if (random < 0.001)
                    powerUps.add(new PowerUp(1, enemy.getX(), enemy.getY()));
                else if (random < 0.101)
                    powerUps.add(new PowerUp(2, enemy.getX(), enemy.getY()));
                else if (random < 0.12)
                    powerUps.add(new PowerUp(3, enemy.getX(), enemy.getY()));
                else if (random < 0.13)
                    powerUps.add(new PowerUp(4, enemy.getX(), enemy.getY()));

                player.addScore(enemy.getType() + enemy.getRank());
                enemies.remove(i);
                i--;

                enemy.explode();
                explosions.add(new Explosion(enemy.getX(), enemy.getY(), enemy.getR(), enemy.getR() + 30));
            }
        }

        // check dead player
        if (player.isDead())
            isRunning = false;

        // player-enemy collision
        if (!player.isRecovering()) {
            int playerX = player.getX();
            int playerY = player.getY();
            int playerR = player.getR();

            for (int i = 0; i < enemies.size(); i++) {
                Enemy enemy = enemies.get(i);

                double enemyX = enemy.getX();
                double enemyY = enemy.getY();
                double enemyR = enemy.getR();

                double dx = playerX - enemyX;
                double dy = playerY - enemyY;
                double distance = Math.sqrt(dx * dx + dy * dy);

                if (distance < playerR + enemyR)
                    player.loseLife();
            }
        }

        // player-powerup collision
        int playerX = player.getX();
        int playerY = player.getY();
        int playerR = player.getR();

        for (int i = 0; i < powerUps.size(); i++) {
            PowerUp powerUp = powerUps.get(i);

            double powerUpX = powerUp.getX();
            double powerUpY = powerUp.getY();
            double powerUpR = powerUp.getR();

            double dx = playerX - powerUpX;
            double dy = playerY - powerUpY;
            double distance = Math.sqrt(dx * dx + dy * dy);

            // collected power-up
            if (distance < playerR + powerUpR) {
                int type = powerUp.getType();

                if (type == 1) {
                    player.gainLife();
                    texts.add(new Text(player.getX(), player.getY(), 2000, "Extra Life"));
                } else if (type == 2) {
                    player.increasePower(1);
                    texts.add(new Text(player.getX(), player.getY(), 2000, "Power"));
                } else if (type == 3) {
                    player.increasePower(2);
                    texts.add(new Text(player.getX(), player.getY(), 2000, "Double Power"));
                } else if (type == 4) {
                    slowDownTimer = System.nanoTime();

                    for (int j = 0; j < enemies.size(); j++) {
                        Enemy enemy = enemies.get(j);
                        enemy.setSlow(true);
                    }

                    texts.add(new Text(player.getX(), player.getY(), 2000, "Slow Down"));
                }

                powerUps.remove(i);
                i--;
            }
        }

        // slow-down update
        if (slowDownTimer != 0) {
            slowDownTimerDiff = (System.nanoTime() - slowDownTimer) / 1000000;

            if (slowDownTimerDiff > slowDownLength) {
                slowDownTimer = 0;

                for (int i = 0; i < enemies.size(); i++) {
                    Enemy enemy = enemies.get(i);
                    enemy.setSlow(false);
                }
            }
        }
    }

    private void gameRender() {
        // draw background
        graphics.setColor(new Color(0, 100, 255));
        graphics.fillRect(0, 0, WIDTH, HEIGHT);

        // draw slow-down screen
        if (slowDownTimer != 0) {
            graphics.setColor(new Color(255, 255, 255, 64));
            graphics.fillRect(0, 0, WIDTH, HEIGHT);
        }

        // draw player
        player.draw(graphics);

        // draw bullet
        for (int i = 0; i < bullets.size(); i++) {
            Bullet bullet = bullets.get(i);
            bullet.draw(graphics);
        }

        // draw enemy
        for (int i = 0; i < enemies.size(); i++) {
            Enemy enemy = enemies.get(i);
            enemy.draw(graphics);
        }

        // draw powerUps
        for (int i = 0; i < powerUps.size(); i++) {
            PowerUp powerUp = powerUps.get(i);
            powerUp.draw(graphics);
        }

        // draw explosion
        for (int i = 0; i < explosions.size(); i++) {
            Explosion explosion = explosions.get(i);
            explosion.draw(graphics);
        }

        // draw text
        for (int i = 0; i < texts.size(); i++) {
            Text text = texts.get(i);
            text.draw(graphics);
        }

        // draw wave number
        if (waveStartTimer != 0) {
            graphics.setFont(new Font("Century Gothic", Font.PLAIN, 18));

            String str = "*** Wave " + waveNumber + " ***";
            FontMetrics fontMetrics = graphics.getFontMetrics();
            Rectangle2D rectangle = fontMetrics.getStringBounds(str, graphics);
            int length = (int) rectangle.getWidth();
            int alpha = (int) (255 * Math.sin(Math.PI * waveStartTimerDiff / waveDelay));

            if (alpha > 255)
                alpha = 255;

            graphics.setColor(new Color(255, 255, 255, alpha));
            graphics.drawString(str, WIDTH / 2 - length / 2, HEIGHT / 2);
        }

        // draw player lives
        for (int i = 0; i < player.getLives(); i++) {
            graphics.setColor(Color.WHITE);
            graphics.fillOval(20 + (20 * i), 20, player.getR() * 2, player.getR() * 2);
            graphics.setStroke(new BasicStroke(3));
            graphics.setColor(Color.WHITE.darker());
            graphics.drawOval(20 + (20 * i), 20, player.getR() * 2, player.getR() * 2);
            graphics.setStroke(new BasicStroke(1));
        }

        // draw player power
        graphics.setColor(Color.YELLOW);
        graphics.fillRect(20, 40, player.getPower() * 8, 8);
        graphics.setColor(Color.YELLOW.darker());
        graphics.setStroke(new BasicStroke(2));
        for (int i = 0; i < player.getRequiredPower(); i++)
            graphics.drawRect(20 + 8 * i, 40, 8, 8);
        graphics.setStroke(new BasicStroke(1));

        // draw info
        graphics.setColor(Color.BLACK);
        graphics.drawString("FPS: " + averageFps, 10, 10);
        graphics.drawString("num bullets: " + bullets.size(), 10, 20);

        // draw player score
        graphics.setColor(Color.WHITE);
        graphics.setFont(new Font("Century Gothic", Font.PLAIN, 14));
        graphics.drawString("Score: " + player.getScore(), WIDTH - 100, 30);

        // draw slow-down meter
        if (slowDownTimer != 0) {
            graphics.setColor(Color.WHITE);
            graphics.drawRect(20, 60, 100, 8);
            graphics.fillRect(20, 60, (int) (100 - 100.0 * slowDownTimerDiff / slowDownLength), 8);
        }
    }

    private void gameDraw() {
        Graphics graphics = this.getGraphics();
        graphics.drawImage(image, 0, 0, null);
        graphics.dispose();
    }

    private void createNewEnemies() {
        enemies.clear();

        if (waveNumber == 1) {
            for (int i = 0; i < 4; i++)
                enemies.add(new Enemy(1, 1));
        } else if (waveNumber == 2) {
            for (int i = 0; i < 8; i++)
                enemies.add(new Enemy(1, 1));
        } else if (waveNumber == 3) {
            for (int i = 0; i < 4; i++)
                enemies.add(new Enemy(1, 1));

            for (int i = 0; i < 2; i++)
                enemies.add(new Enemy(1, 2));
        } else if (waveNumber == 4) {
            enemies.add(new Enemy(1, 3));
            enemies.add(new Enemy(1, 4));

            for (int i = 0; i < 4; i++)
                enemies.add(new Enemy(2, 1));
        } else if (waveNumber == 5) {
            enemies.add(new Enemy(1, 4));
            enemies.add(new Enemy(1, 3));
            enemies.add(new Enemy(2, 3));
        } else if (waveNumber == 6) {
            enemies.add(new Enemy(1, 3));

            for (int i = 0; i < 4; i++)
                enemies.add(new Enemy(2, 1));

            for (int i = 0; i < 4; i++)
                enemies.add(new Enemy(3, 1));
        } else if (waveNumber == 7) {
            enemies.add(new Enemy(1, 3));
            enemies.add(new Enemy(2, 3));
            enemies.add(new Enemy(3, 3));
        } else if (waveNumber == 8) {
            enemies.add(new Enemy(1, 4));
            enemies.add(new Enemy(2, 4));
            enemies.add(new Enemy(3, 4));
        } else if (waveNumber == 9)
            isRunning = false;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();

        if (keyCode == KeyEvent.VK_LEFT)
            player.setLeft(true);

        if (keyCode == KeyEvent.VK_RIGHT)
            player.setRight(true);

        if (keyCode == KeyEvent.VK_UP)
            player.setUp(true);

        if (keyCode == KeyEvent.VK_DOWN)
            player.setDown(true);

        if (keyCode == KeyEvent.VK_Z)
            player.setFiring(true);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();

        if (keyCode == KeyEvent.VK_LEFT)
            player.setLeft(false);

        if (keyCode == KeyEvent.VK_RIGHT)
            player.setRight(false);

        if (keyCode == KeyEvent.VK_UP)
            player.setUp(false);

        if (keyCode == KeyEvent.VK_DOWN)
            player.setDown(false);

        if (keyCode == KeyEvent.VK_Z)
            player.setFiring(false);
    }
}

class Player {
    private int x;
    private int y;
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

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getR() {
        return r;
    }

    public boolean isRecovering() {
        return recovering;
    }

    public int getLives() {
        return lives;
    }

    public boolean isDead() {
        return lives <= 0;
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

    public int getScore() {
        return score;
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
            if (power > requiredPower[powerLevel]) {
                power = requiredPower[powerLevel];
            }
            return;
        }

        if (power >= requiredPower[powerLevel]) {
            power -= requiredPower[powerLevel];
            powerLevel++;
        }
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
            long elapsedTime = (System.nanoTime() - firingTimer) / 1000000;

            if (elapsedTime > firingDelay) {
                firingTimer = System.nanoTime();

                List<Bullet> bullets = GamePanel.bullets;
                if (powerLevel < 2) {
                    bullets.add(new Bullet(270, x, y));
                } else if (powerLevel < 4) {
                    bullets.add(new Bullet(270, x + 5, y));
                    bullets.add(new Bullet(270, x - 5, y));
                } else {
                    bullets.add(new Bullet(270, x, y));
                    bullets.add(new Bullet(275, x + 5, y));
                    bullets.add(new Bullet(265, x - 5, y));
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
            graphics.fillOval(x - r, y - r, 2 * r, 2 * r);

            graphics.setStroke(new BasicStroke(3));
            graphics.setColor(color2.darker());
            graphics.drawOval(x - r, y - r, 2 * r, 2 * r);

            graphics.setStroke(new BasicStroke(1));
        } else {
            graphics.setColor(color1);
            graphics.fillOval(x - r, y - r, 2 * r, 2 * r);

            graphics.setStroke(new BasicStroke(3));
            graphics.setColor(color1.darker());
            graphics.drawOval(x - r, y - r, 2 * r, 2 * r);

            graphics.setStroke(new BasicStroke(1));
        }
    }
}

class Bullet {
    private double x;
    private double y;
    private int r;

    private double dx;
    private double dy;
    private double rad;
    private double speed;

    private Color color1;

    public Bullet(int angle, double x, double y) {
        this.x = x;
        this.y = y;
        this.r = 2;

        rad = Math.toRadians(angle);
        speed = 10;
        dx = Math.cos(rad) * speed;
        dy = Math.sin(rad) * speed;

        color1 = Color.YELLOW;
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

    public boolean update() {
        x += dx;
        y += dy;

        if (x < -r)
            return true;

        if (x > GamePanel.WIDTH + r)
            return true;

        if (y < -r)
            return true;

        if (y > GamePanel.HEIGHT + r)
            return true;

        return false;
    }

    public void draw(Graphics2D graphics) {
        graphics.setColor(color1);
        graphics.fillOval((int) (x - r), (int) (y - r), 2 * r, 2 * r);
    }
}

class Enemy {
    private double x;
    private double y;
    private int r;

    private double dx;
    private double dy;
    private double rad;
    private double speed;

    private int health;
    private int type;
    private int rank;

    private Color color1;

    private boolean ready;
    private boolean dead;

    private boolean hit;
    private long hitTimer;

    private boolean slow;

    public Enemy(int type, int rank) {
        this.type = type;
        this.rank = rank;

        if (type == 1) {
            // default enemy

            color1 = new Color(0, 0, 255, 128);

            if (rank == 1) {
                speed = 2;
                r = 5;
                health = 1;
            } else if (rank == 2) {
                speed = 2;
                r = 10;
                health = 2;
            } else if (rank == 3) {
                speed = 1.5;
                r = 30;
                health = 4;
            }
        } else if (type == 2) {
            // stronger, faster default

            color1 = new Color(255, 0, 0, 128);

            if (rank == 1) {
                speed = 3;
                r = 5;
                health = 2;
            } else if (rank == 2) {
                speed = 3;
                r = 10;
                health = 3;
            } else if (rank == 3) {
                speed = 2.5;
                r = 20;
                health = 3;
            } else if (rank == 4) {
                speed = 2.5;
                r = 30;
                health = 4;
            }
        } else if (type == 3) {
            // slow, but hard to kill

            color1 = new Color(0, 255, 0, 128);

            if (rank == 1) {
                speed = 1.5;
                r = 5;
                health = 3;
            } else if (rank == 2) {
                speed = 1.5;
                r = 10;
                health = 4;
            } else if (rank == 3) {
                speed = 1.5;
                r = 25;
                health = 5;
            } else if (rank == 4) {
                speed = 1.5;
                r = 45;
                health = 5;
            }
        }

        x = Math.random() * GamePanel.WIDTH / 2 + GamePanel.HEIGHT / 4;
        y = -r;

        double angle = Math.random() * 140 + 20;
        rad = Math.toRadians(angle);

        dx = Math.cos(rad) * speed;
        dy = Math.sin(rad) * speed;

        ready = false;
        dead = false;

        hit = false;
        hitTimer = 0;
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

    public int getType() {
        return type;
    }

    public int getRank() {
        return rank;
    }

    public boolean isDead() {
        return dead;
    }

    public void setSlow(boolean slow) {
        this.slow = slow;
    }

    public void hit() {
        health--;

        if (health <= 0)
            dead = true;

        hit = true;
        hitTimer = System.nanoTime();
    }

    public void explode() {
        if (rank > 1) {
            int amount = 0;

            if (type == 1)
                amount = 3;
            else if (type == 2)
                amount = 3;
            else if (type == 3)
                amount = 4;

            for (int i = 0; i < amount; i++) {
                Enemy enemy = new Enemy(getType(), getRank() - 1);

                enemy.setSlow(slow);
                enemy.x = this.x;
                enemy.y = this.y;
                double angle = 0;
                if (!ready)
                    angle = Math.random() * 140 + 20;
                else
                    angle = Math.random() * 360;
                enemy.rad = Math.toRadians(angle);

                GamePanel.enemies.add(enemy);
            }
        }
    }

    public void update() {
        if (slow) {
            x += dx * 0.3;
            y += dy * 0.3;
        } else {
            x += dx;
            y += dy;
        }

        if (!ready) {
            if (x > r && x < GamePanel.WIDTH - r && y > r && y < GamePanel.HEIGHT - r) {
                ready = true;
            }
        }

        if (x < r && dx < 0)
            dx = -dx;

        if (y < r && dy < 0)
            dy = -dy;

        if (x > GamePanel.WIDTH - r && dx > 0)
            dx = -dx;

        if (y > GamePanel.HEIGHT - r && dy > 0)
            dy = -dy;

        if (hit) {
            long elapsed = (System.nanoTime() - hitTimer) / 1000000;

            if (elapsed > 50) {
                hit = false;
                hitTimer = 0;
            }
        }
    }

    public void draw(Graphics2D graphics) {
        if (hit) {
            graphics.setColor(Color.WHITE);
            graphics.fillOval((int) (x - r), (int) (y - r), 2 * r, 2 * r);

            graphics.setStroke(new BasicStroke(3));
            graphics.setColor(Color.WHITE.darker());
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

class PowerUp {
    private double x;
    private double y;
    private int r;

    private int type;
    private Color color1;

    // 1) +1 life
    // 2) +1 power
    // 3) +2 power
    // 4) slow down time

    public PowerUp(int type, double x, double y) {
        this.type = type;

        this.x = x;
        this.y = y;

        if (type == 1) {
            color1 = Color.PINK;
            r = 3;
        } else if (type == 2) {
            color1 = Color.YELLOW;
            r = 3;
        } else if (type == 3) {
            color1 = Color.YELLOW;
            r = 5;
        } else if (type == 4) {
            color1 = Color.WHITE;
            r = 3;
        }
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

    public int getType() {
        return type;
    }

    public boolean update() {
        y += 2;

        if (y > GamePanel.HEIGHT + r)
            return true;

        return false;
    }

    public void draw(Graphics2D graphics) {
        graphics.setColor(color1);
        graphics.fillRect((int) (x - r), (int) (y - r), 2 * r, 2 * r);

        graphics.setStroke(new BasicStroke(3));
        graphics.setColor(color1.darker());
        graphics.drawOval((int) (x - r), (int) (y - r), 2 * r, 2 * r);
        graphics.setStroke(new BasicStroke(1));
    }
}

class Explosion {
    private double x;
    private double y;
    private int r;
    private int maxRadius;

    public Explosion(double x, double y, int r, int maxRadius) {
        this.x = x;
        this.y = y;
        this.r = r;
        this.maxRadius = maxRadius;
    }

    public boolean update() {
        r += 2;

        if (r >= maxRadius)
            return true;

        return false;
    }

    public void draw(Graphics2D graphics) {
        graphics.setColor(new Color(255, 255, 255, 128));
        graphics.setStroke(new BasicStroke(3));
        graphics.drawOval((int) (x - r), (int) (y - r), 2 * r, 2 * r);
        graphics.setStroke(new BasicStroke(1));
    }
}

class Text {
    private double x;
    private double y;
    private long time;
    private String str;

    private long start;

    public Text(double x, double y, long time, String str) {
        this.x = x;
        this.y = y;
        this.time = time;
        this.str = str;

        start = System.nanoTime();
    }

    public boolean update() {
        long elapsed = (System.nanoTime() - start) / 1000000;

        if (elapsed > time)
            return true;

        return false;
    }

    public void draw(Graphics2D graphics) {
        graphics.setFont(new Font("Century Gothic", Font.PLAIN, 12));

        long elapsed = (System.nanoTime() - start) / 1000000;
        int alpha = (int) (255 * Math.sin(Math.PI * elapsed / time));
        if (alpha > 255)
            alpha = 255;

        graphics.setColor(new Color(255, 255, 255, alpha));
        FontMetrics fontMetrics = graphics.getFontMetrics();
        Rectangle2D rectangle = fontMetrics.getStringBounds(str, graphics);
        int length = (int) rectangle.getWidth();
        graphics.drawString(str, (int) (x - (length / 2)), (int) y);
    }
}
