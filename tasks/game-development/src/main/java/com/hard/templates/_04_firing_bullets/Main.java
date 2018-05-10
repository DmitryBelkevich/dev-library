package com.hard.templates._04_firing_bullets;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

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

    private Player player;

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
    }

    private void gameRender() {
        graphics.setColor(new Color(0, 100, 255));
        graphics.fillRect(0, 0, WIDTH, HEIGHT);
        graphics.setColor(Color.BLACK);
        graphics.drawString("FPS: " + averageFps, 10, 10);

        player.draw(graphics);
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
