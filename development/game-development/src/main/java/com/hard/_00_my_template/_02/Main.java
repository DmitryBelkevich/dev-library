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
    private JFrame frame = new JFrame("Game");

    private static final double SCALE = 1;
    private static final int WIDTH = (int) (640 * SCALE);
    private static final int HEIGHT = (int) (480 * SCALE);

    // graphics
    private BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
    private Graphics2D graphics = (Graphics2D) image.getGraphics();

    // entity
    private int w = 200;
    private int h = 200;

    private double x = WIDTH / 2 - w / 2;
    private double y = HEIGHT / 2 - h / 2;

    private double dx = 0;
    private double dy = 0;

    private double speed = 5;

    /**
     * Game loop
     */

    public void run() {
        createGui();
        init();

        while (isRunning) {
            // update

            x += dx;
            y += dy;

            if (x <= 0)
                x = 0;

            if (y <= 0)
                y = 0;

            if (x >= WIDTH - w)
                x = WIDTH - w;

            if (y >= HEIGHT - h)
                y = HEIGHT - h;

            // draw
            graphics.setColor(new Color(255, 0, 0, 255));
            graphics.fillOval((int) x, (int) y, w, h);

            String str =
                    "x: " + x
                            + ";\t    y: " + y
                            + ";\t    dx: " + dx
                            + ";\t    dy: " + dy
                            + ";\t    speed: " + speed;
            graphics.setColor(new Color(0, 255, 0, 255));
            graphics.drawString(str, 50, 50);

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
        frame.setSize(WIDTH, HEIGHT);

        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        frame.addKeyListener(keyListener);

//        frame.pack();
        frame.setVisible(true);
    }

    private void init() {
        isRunning = true;

        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
    }

    private void display(BufferedImage image) {
        Graphics graphics = frame.getGraphics();
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
            if (e.getKeyCode() == KeyEvent.VK_ESCAPE)
                isRunning = false;

            if (e.getKeyCode() == KeyEvent.VK_LEFT)
                dx = -speed;

            if (e.getKeyCode() == KeyEvent.VK_RIGHT)
                dx = speed;

            if (e.getKeyCode() == KeyEvent.VK_UP)
                dy = -speed;

            if (e.getKeyCode() == KeyEvent.VK_DOWN)
                dy = speed;
        }

        @Override
        public void keyReleased(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_LEFT)
                dx = 0;

            if (e.getKeyCode() == KeyEvent.VK_RIGHT)
                dx = 0;

            if (e.getKeyCode() == KeyEvent.VK_UP)
                dy = 0;

            if (e.getKeyCode() == KeyEvent.VK_DOWN)
                dy = 0;
        }
    };
}
