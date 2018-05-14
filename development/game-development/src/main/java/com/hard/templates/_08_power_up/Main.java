package com.hard.templates._08_power_up;

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
        JFrame window = new JFrame("platformer game");

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

    private long waveStartTimer;
    private long waveStartTimerDiff;
    private int waveNumber;
    private boolean waveStart;
    private int waveDelay = 2000;

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

        waveStartTimer = 0;
        waveStartTimerDiff = 0;
        waveNumber = 0;
        waveStart = true;

        long totalTime = 0;

        int frameCount = 0;
        int maxFrameCount = 30;

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

                double dist = Math.sqrt(dx * dx + dy * dy);

                if (dist < bulletR + enemyR) {
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
                else if (random < 0.02)
                    powerUps.add(new PowerUp(3, enemy.getX(), enemy.getY()));
                else if (random < 0.12)
                    powerUps.add(new PowerUp(2, enemy.getX(), enemy.getY()));

                player.addScore(enemy.getType() + enemy.getRank());
                enemies.remove(i);
                i--;
            }
        }

        // player enemy collision
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
                double dist = Math.sqrt(dx * dx + dy * dy);

                if (dist < playerR + enemyR)
                    player.loseLife();
            }
        }
    }

    private void gameRender() {
        // draw background
        graphics.setColor(new Color(0, 100, 255));
        graphics.fillRect(0, 0, WIDTH, HEIGHT);

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

        // draw wave number
        if (waveStartTimer != 0) {
            graphics.setFont(new Font("Century Gothic", Font.PLAIN, 18));

            String str = "*** Wave " + waveNumber + " ***";
            FontMetrics fontMetrics = graphics.getFontMetrics();
            Rectangle2D rectangle2D = fontMetrics.getStringBounds(str, graphics);
            int length = (int) rectangle2D.getWidth();
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

        // draw info
        graphics.setColor(Color.BLACK);
        graphics.drawString("FPS: " + averageFps, 10, 10);
        graphics.drawString("num bullets: " + bullets.size(), 10, 20);

        // draw player score
        graphics.setColor(Color.WHITE);
        graphics.setFont(new Font("Century Gothic", Font.PLAIN, 14));
        graphics.drawString("Score: " + player.getScore(), WIDTH - 100, 30);
    }

    private void gameDraw() {
        Graphics graphics = this.getGraphics();
        graphics.drawImage(image, 0, 0, null);
        graphics.dispose();
    }

    private void createNewEnemies() {
        enemies.clear();

        if (waveNumber == 1) {
            for (int i = 0; i < 4; i++) {
                enemies.add(new Enemy(1, 1));
            }
        }

        if (waveNumber == 2) {
            for (int i = 0; i < 8; i++) {
                enemies.add(new Enemy(1, 1));
            }
        }
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

    public void addScore(int i) {
        score += i;
    }

    public void loseLife() {
        lives--;
        recovering = true;
        recoveryTimer = System.nanoTime();
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

        if (firing) {
            long elapsedTime = (System.nanoTime() - firingTimer) / 1000000;

            if (elapsedTime > firingDelay) {
                List<Bullet> bullets = GamePanel.bullets;
                bullets.add(new Bullet(270, x, y));

                firingTimer = System.nanoTime();
            }
        }

        long elapsed = (System.nanoTime() - recoveryTimer) / 1000000;
        if (elapsed > 2000) {
            recovering = false;
            recoveryTimer = 0;
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

    public Enemy(int type, int rank) {
        this.type = type;
        this.rank = rank;

        // default enemy
        if (type == 1) {
            color1 = Color.BLUE;

            if (rank == 1) {
                speed = 2;
                r = 5;
                health = 1;
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

    public void hit() {
        health--;

        if (health <= 0) {
            dead = true;
        }
    }

    public void update() {
        x += dx;
        y += dy;

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
    }

    public void draw(Graphics2D graphics) {
        graphics.setColor(color1);
        graphics.fillOval((int) (x - r), (int) (y - r), 2 * r, 2 * r);

        graphics.setStroke(new BasicStroke(3));
        graphics.setColor(color1.darker());
        graphics.drawOval((int) (x - r), (int) (y - r), 2 * r, 2 * r);
        graphics.setStroke(new BasicStroke(1));
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
