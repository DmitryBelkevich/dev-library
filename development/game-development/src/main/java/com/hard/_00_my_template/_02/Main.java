package com.hard._00_my_template._02;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

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

    private double speed = 0;

    private double dx = 0;
    private double dy = 0;

    // moving
    private boolean left = false;
    private boolean right = false;
    private boolean up = false;
    private boolean down = false;

    // time
    private double elapsed;

    // animation
    private Animation animation;

    /**
     * Game loop
     */

    public void run() {
        createGui();
        init();

        while (isRunning) {
            /**
             * nanos / 1 = nanos
             * nanos / 1_000 = micro
             * nanos / 1_000_000 = mills
             * nanos / 1_000_000_000 = seconds
             *
             * seconds * 1 = seconds
             * seconds * 1_000 = mills
             * seconds * 1_000_000 = micro
             * seconds * 1_000_000_000 = nanos
             */

            long start = System.nanoTime();

            elapsed /= 20;

            update(elapsed);
            draw(elapsed);
            display(image);
            clearScreen(graphics);

            long finish = System.nanoTime();

            elapsed = (double) (finish - start) / 1_000_000;
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

        animation = new Animation();
    }

    private void update(double time) {
        /**
         * check moving
         */

        if (left) {
            speed = 5;
            dx = -speed;
        }

        if (right) {
            speed = 5;
            dx = speed;
        }

        if (up) {
            speed = 5;
            dy = -speed;
        }

        if (down) {
            speed = 5;
            dy = speed;
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

        if (x > WIDTH - w)
            x = WIDTH - w;

        if (y > HEIGHT - h)
            y = HEIGHT - h;

        /**
         * stop moving
         */

        /**
         * animation
         */
        animation.update(time);
    }

    private void draw(double time) {
        // drawing entity (circle)
        graphics.setColor(new Color(255, 0, 0, 255));
        graphics.fillOval((int) x, (int) y, w, h);

        // drawing entity (sprite)
        animation.draw(graphics, x, y);

        // drawing console
        drawConsole();
    }

    private void drawConsole() {
        int step = 20;

        graphics.setColor(new Color(0, 255, 0, 255));

//        if (x != 0) graphics.setColor(new Color(225, 0, 0, 255));
        graphics.drawString("x=" + x, 50, 1 * step);
//        graphics.setColor(new Color(0, 255, 0, 255));

//        if (y != 0) graphics.setColor(new Color(225, 0, 0, 255));
        graphics.drawString("y=" + y, 50, 2 * step);
//        graphics.setColor(new Color(0, 255, 0, 255));

        if (dx != 0) graphics.setColor(new Color(225, 0, 0, 255));
        graphics.drawString("dx=" + dx, 50, 3 * step);
        graphics.setColor(new Color(0, 255, 0, 255));

        if (dy != 0) graphics.setColor(new Color(225, 0, 0, 255));
        graphics.drawString("dy=" + dy, 50, 4 * step);
        graphics.setColor(new Color(0, 255, 0, 255));

        if (speed != 0) graphics.setColor(new Color(225, 0, 0, 255));
        graphics.drawString("speed=" + speed, 50, 5 * step);
        graphics.setColor(new Color(0, 255, 0, 255));


        if (left) graphics.setColor(new Color(225, 0, 0, 255));
        graphics.drawString("left=" + left, 50, 7 * step);
        graphics.setColor(new Color(0, 255, 0, 255));

        if (right) graphics.setColor(new Color(225, 0, 0, 255));
        graphics.drawString("right=" + right, 50, 8 * step);
        graphics.setColor(new Color(0, 255, 0, 255));

        if (up) graphics.setColor(new Color(225, 0, 0, 255));
        graphics.drawString("up=" + up, 50, 9 * step);
        graphics.setColor(new Color(0, 255, 0, 255));

        if (down) graphics.setColor(new Color(225, 0, 0, 255));
        graphics.drawString("down=" + down, 50, 10 * step);
        graphics.setColor(new Color(0, 255, 0, 255));
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

            if (keyCode == KeyEvent.VK_LEFT) {
                left = true;
            }

            if (keyCode == KeyEvent.VK_RIGHT) {
                right = true;
            }

            if (keyCode == KeyEvent.VK_UP) {
                up = true;
            }

            if (keyCode == KeyEvent.VK_DOWN) {
                down = true;
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
            int keyCode = e.getKeyCode();

            if (keyCode == KeyEvent.VK_LEFT) {
                left = false;
                speed = 0;
                dx = speed;
            }

            if (keyCode == KeyEvent.VK_RIGHT) {
                right = false;
                speed = 0;
                dx = speed * 0;
            }

            if (keyCode == KeyEvent.VK_UP) {
                up = false;
                speed = 0;
                dy = speed;
            }

            if (keyCode == KeyEvent.VK_DOWN) {
                down = false;
                speed = 0;
                dy = speed;
            }
        }
    };
}

class Animation {
    private List<BufferedImage> frames;

    private double speedFrame = 0.145;
    private double currentFrame = 0;

    public Animation() {
        Class<?> clazz = this.getClass();
        InputStream inputStream = clazz.getResourceAsStream("/player.png");

        BufferedImage sprite = null;
        try {
            sprite = ImageIO.read(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        frames = new ArrayList<>();

        int wSprite = 95;
        int hSprite = 75;

        BufferedImage frame1 = sprite.getSubimage(0 + wSprite * 0, 220, wSprite, hSprite);
        BufferedImage frame2 = sprite.getSubimage(0 + wSprite * 1, 220, wSprite, hSprite);
        BufferedImage frame3 = sprite.getSubimage(0 + wSprite * 2, 220, wSprite, hSprite);

        frames.add(frame1);
        frames.add(frame2);
        frames.add(frame3);
    }

    public void update(double time) {
        currentFrame += speedFrame * time;
        if (currentFrame >= frames.size())
            currentFrame = 0;
    }

    public void draw(Graphics graphics, double x, double y) {
        BufferedImage frame = frames.get((int) currentFrame);

        graphics.drawImage(frame, (int) x, (int) y, null);
    }
}
