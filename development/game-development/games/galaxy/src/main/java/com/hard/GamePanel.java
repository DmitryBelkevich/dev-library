package com.hard;

import com.hard.entities.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class GamePanel extends JPanel implements Runnable, KeyListener {
    public static int WIDTH = 400;
    public static int HEIGHT = 400;

    private Thread thread;
    private boolean isRunning;

    private BufferedImage image;
    private Graphics2D graphics;

    private int FPS = 30;
    private double averageFPS;

    public static Player player;
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
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setFocusable(true);
        requestFocus();
    }

    public void addNotify() {
        super.addNotify();
        if (thread == null) {
            thread = new Thread(this);
            thread.start();
        }
        addKeyListener(this);
    }

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
        waveStart = true;
        waveNumber = 0;

        long startTime;
        long urdTimeMillis;
        long waitTime;
        long totalTime = 0;

        int frameCount = 0;
        int maxFrameCount = 30;

        long targetTime = 1000 / FPS;

        // game loop
        while (isRunning) {
            startTime = System.nanoTime();

            gameUpdate();
            gameRender();
            gameDraw();

            urdTimeMillis = (System.nanoTime() - startTime) / 1000000;

            waitTime = targetTime - urdTimeMillis;
            if (waitTime < 0)
                waitTime = 0;

            try {
                Thread.sleep(waitTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            frameCount++;
            if (frameCount == maxFrameCount) {
                averageFPS = 1000.0 / ((totalTime / frameCount) / 1000000);
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

        String str2 = "Final Score: " + player.getScore();
        FontMetrics fontMetrics2 = graphics.getFontMetrics();
        Rectangle2D rectangle2 = fontMetrics2.getStringBounds(str2, graphics);
        int length2 = (int) rectangle2.getWidth();
        graphics.drawString(str2, (WIDTH - length2) / 2, HEIGHT / 2 + 30);

        gameDraw();
    }

    private void gameUpdate() {
        // new wave
        if (waveStartTimer == 0 && enemies.size() == 0) {
            waveNumber++;
            waveStart = false;
            waveStartTimer = System.nanoTime();
        } else {
            waveStartTimerDiff = (System.nanoTime() - waveStartTimer) / 1000000;
            if (waveStartTimerDiff > waveDelay) {
                waveStart = true;
                waveStartTimer = 0;
                waveStartTimerDiff = 0;
            }
        }

        // create enemies
        if (waveStart && enemies.size() == 0) {
            createNewEnemies();
        }

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

        // enemy update
        for (int i = 0; i < enemies.size(); i++) {
            Enemy enemy = enemies.get(i);
            enemy.update();
        }

        // power-up update
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

        // bullet-enemy collision
        for (int i = 0; i < bullets.size(); i++) {
            Bullet bullet = bullets.get(i);

            double bulletX = bullet.getX();
            double bulletY = bullet.getY();
            int bulletR = bullet.getR();

            for (int j = 0; j < enemies.size(); j++) {
                Enemy enemy = enemies.get(j);

                double enemyX = enemy.getX();
                double enemyY = enemy.getY();
                int enemyR = enemy.getR();

                double distanceX = bulletX - enemyX;
                double distanceY = bulletY - enemyY;
                double distance = Math.sqrt(distanceX * distanceX + distanceY * distanceY);

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
                // chance for power-up
                double random = Math.random();

                if (random < 0.001)
                    powerUps.add(new PowerUp(enemy.getX(), enemy.getY(), 1));
                else if (random < 0.020)
                    powerUps.add(new PowerUp(enemy.getX(), enemy.getY(), 3));
                else if (random < 0.120)
                    powerUps.add(new PowerUp(enemy.getX(), enemy.getY(), 2));
                else if (random < 0.130)
                    powerUps.add(new PowerUp(enemy.getX(), enemy.getY(), 4));

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
            double playerX = player.getX();
            double playerY = player.getY();
            int playerR = player.getR();

            for (int i = 0; i < enemies.size(); i++) {
                Enemy enemy = enemies.get(i);

                double enemyX = enemy.getX();
                double enemyY = enemy.getY();
                int enemyR = enemy.getR();

                double distanceX = playerX - enemyX;
                double distanceY = playerY - enemyY;
                double distance = Math.sqrt(distanceX * distanceX + distanceY * distanceY);

                if (distance < playerR + enemyR)
                    player.loseLife();
            }
        }

        // player-power-up collision
        double playerX = player.getX();
        double playerY = player.getY();
        int playerR = player.getR();

        for (int i = 0; i < powerUps.size(); i++) {
            PowerUp powerUp = powerUps.get(i);

            double powerUpX = powerUp.getX();
            double powerUpY = powerUp.getY();
            int powerUpR = powerUp.getR();

            double distanceX = playerX - powerUpX;
            double distanceY = playerY - powerUpY;
            double distance = Math.sqrt(distanceX * distanceX + distanceY * distanceY);

            // collected power-up
            if (distance < playerR + powerUpR) {
                int type = powerUp.getType();

                if (type == 1) {
                    player.gainLife();
                    texts.add(new Text(player.getX(), player.getY(), "Extra Life", 2000));
                }

                if (type == 2) {
                    player.increasePower(1);
                    texts.add(new Text(player.getX(), player.getY(), "Power", 2000));
                }

                if (type == 3) {
                    player.increasePower(2);
                    texts.add(new Text(player.getX(), player.getY(), "Double Power", 2000));
                }

                if (type == 4) {
                    slowDownTimer = System.nanoTime();

                    for (int j = 0; j < enemies.size(); j++) {
                        Enemy enemy = enemies.get(j);
                        enemy.setSlow(true);
                    }

                    texts.add(new Text(player.getX(), player.getY(), "Slow Down", 2000));
                }

                powerUps.remove(i);
                i--;
            }
        }

        // slowdown update
        if (slowDownTimer != 0) {
            slowDownTimerDiff = (System.nanoTime() - slowDownTimer) / 1000000;

            if (slowDownTimerDiff > slowDownLength) {
                slowDownTimer = 0;

                for (int j = 0; j < enemies.size(); j++) {
                    Enemy enemy = enemies.get(j);
                    enemy.setSlow(false);
                }
            }
        }
    }

    private void gameRender() {
        // draw background
        graphics.setColor(new Color(0, 100, 255));
        graphics.fillRect(0, 0, WIDTH, HEIGHT);

        // draw slowdown screen
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

        // draw power-ups
        for (int i = 0; i < powerUps.size(); i++) {
            PowerUp powerUp = powerUps.get(i);
            powerUp.draw(graphics);
        }

        // draw explosions
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

            String str = "*** W A V E - " + waveNumber + " ***";
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
        int playerLives = player.getLives();
        for (int i = 0; i < playerLives; i++) {
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

        // draw player score
        graphics.setColor(Color.WHITE);
        graphics.setFont(new Font("Century Gothic", Font.PLAIN, 14));
        graphics.drawString("Score: " + player.getScore(), WIDTH - 100, 30);

        // draw slowdown meter
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
    public void keyTyped(KeyEvent key) {

    }

    @Override
    public void keyPressed(KeyEvent key) {
        int keyCode = key.getKeyCode();

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
    public void keyReleased(KeyEvent key) {
        int keyCode = key.getKeyCode();

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
