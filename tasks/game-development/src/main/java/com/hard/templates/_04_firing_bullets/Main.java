package com.hard.templates._04_firing_bullets;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        JFrame window = new JFrame("platformer game");

        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);

        window.setContentPane(new GamePanel());

        window.setVisible(true);
        window.pack();
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

    private static Player player;
    public static List<Bullet> bullets;

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

        player = new Player();
        bullets = new ArrayList<>();

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
        player.update();

        for (int i = 0; i < bullets.size(); i++) {
            Bullet bullet = bullets.get(i);
            boolean remove = bullet.update();

            if (remove) {
                bullets.remove(i);
                i--;
            }
        }
    }

    private void gameRender() {
        graphics.setColor(new Color(0, 100, 255));
        graphics.fillRect(0, 0, WIDTH, HEIGHT);
        graphics.setColor(Color.BLACK);
        graphics.drawString("FPS: " + averageFps, 10, 10);
        graphics.drawString("num bullets: " + bullets.size(), 10, 20);

        player.draw(graphics);

        for (int i = 0; i < bullets.size(); i++) {
            Bullet bullet = bullets.get(i);
            bullet.draw(graphics);
        }
    }

    private void gameDraw() {
        Graphics graphics = this.getGraphics();
        graphics.drawImage(image, 0, 0, null);
        graphics.dispose();
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

    private int lives;

    private Color color1;
    private Color color2;

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
    }

    public void draw(Graphics2D graphics) {
        graphics.setColor(color1);
        graphics.fillOval(x - r, y - r, 2 * r, 2 * r);

        graphics.setStroke(new BasicStroke(3));
        graphics.setColor(color1.darker());
        graphics.drawOval(x - r, y - r, 2 * r, 2 * r);

        graphics.setStroke(new BasicStroke(1));
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
        speed = 15;
        dx = Math.cos(rad) * speed;
        dy = Math.sin(rad) * speed;

        color1 = Color.YELLOW;
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
