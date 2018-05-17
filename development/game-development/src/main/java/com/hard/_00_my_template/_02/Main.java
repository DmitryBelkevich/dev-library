package com.hard._00_my_template._02;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

public class Main {
    public static void main(String[] args) {
        Game game = new Game();
        game.run();
    }
}

class Game {
    private volatile boolean isRunning;
    private JPanel panel;

    private static final String TITLE = "Game";
    private static final double SCALE = 1;
    private static final int WIDTH = (int) (640 * SCALE);
    private static final int HEIGHT = (int) (480 * SCALE);

    // graphics
    private BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
    private Graphics2D graphics = (Graphics2D) image.getGraphics();

    // entity
    private int w = 64;
    private int h = 64;

    private double x = WIDTH / 2 - w / 2;
    private double y = HEIGHT / 2 - h / 2;

    private double dx = 0;
    private double dy = 0;

    private double speed = 5;

    // moving
    private boolean left = false;
    private boolean right = false;
    private boolean up = false;
    private boolean down = false;

    /**
     * Game loop
     */

    public void run() {
        createGui();
        init();

        while (isRunning) {
            update();
            draw();
            display(image);
            clearScreen(graphics);

            long waitingTime = 30;
            try {
                Thread.sleep(waitingTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.exit(0);
    }

    private void createGui() {
        JFrame frame = new JFrame(TITLE);

        frame.setSize(WIDTH, HEIGHT);

        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        panel = new JPanel();
        panel.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        frame.setContentPane(panel);

        frame.pack();
        frame.setVisible(true);

        frame.addKeyListener(keyListener);
    }

    private void init() {
        isRunning = true;

        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
    }

    private void update() {
        // check moving

        if (left)
            dx = -speed;

        if (right)
            dx = speed;

        if (up)
            dy = -speed;

        if (down)
            dy = speed;

        // moving

        x += dx;
        y += dy;

        // check collision

        if (x <= 0)
            x = 0;

        if (y <= 0)
            y = 0;

        if (x >= WIDTH - w)
            x = WIDTH - w;

        if (y >= HEIGHT - h)
            y = HEIGHT - h;

        // stop moving

        dx = 0;
        dy = 0;
    }

    private void draw() {
        // drawing entity
        graphics.setColor(new Color(255, 0, 0, 255));
        graphics.fillOval((int) x, (int) y, w, h);

        // drawing console
        String str =
                "x: " + x
                        + ";\t    y: " + y
                        + ";\t    dx: " + dx
                        + ";\t    dy: " + dy
                        + ";\t    speed: " + speed;
        graphics.setColor(new Color(0, 255, 0, 255));
        graphics.drawString(str, 50, 50);
    }

    private void display(BufferedImage image) {
        Graphics graphics = panel.getGraphics();
        graphics.drawImage(image, 0, 0, null);
        graphics.dispose();
    }

    private void clearScreen(Graphics graphics) {
        graphics.setColor(new Color(0, 0, 0, 255));
        graphics.fillRect(0, 0, WIDTH, HEIGHT);
    }

    private KeyListener keyListener = new KeyListener() {
        @Override
        public void keyTyped(KeyEvent e) {

        }

        @Override
        public void keyPressed(KeyEvent e) {
            int keyCode = e.getKeyCode();

            if (keyCode == KeyEvent.VK_ESCAPE)
                isRunning = false;

            if (keyCode == KeyEvent.VK_LEFT)
                left = true;

            if (keyCode == KeyEvent.VK_RIGHT)
                right = true;

            if (keyCode == KeyEvent.VK_UP)
                up = true;

            if (keyCode == KeyEvent.VK_DOWN)
                down = true;
        }

        @Override
        public void keyReleased(KeyEvent e) {
            int keyCode = e.getKeyCode();

            if (keyCode == KeyEvent.VK_LEFT)
                left = false;

            if (keyCode == KeyEvent.VK_RIGHT)
                right = false;

            if (keyCode == KeyEvent.VK_UP)
                up = false;

            if (keyCode == KeyEvent.VK_DOWN)
                down = false;
        }
    };
}
