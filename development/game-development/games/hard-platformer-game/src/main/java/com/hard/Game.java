package com.hard;

import com.hard.entities.Entity;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

public class Game {
    private volatile boolean running;
    private JPanel panel;

    // tiles
    private static final int H_TILE = 32;
    private static final int W_TILE = H_TILE;

    private static final int H_MAP = 12;
    private static final int W_MAP = 40;

    // screen
    private static final String TITLE = "Game";
    private static final double SCALE = 0.7;
    public static final int WIDTH = (int) (H_TILE * 20 * SCALE);
    public static final int HEIGHT = (int) (W_TILE * 15 * SCALE);

    public static final double GRAVITY = 1.2;

    // graphics
    private BufferedImage image;
    private Graphics2D graphics;

    // time
    private double time;

    // entities
    private Entity entity;

    // tilemap
    public static final String[] tilemap = {
            "BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB",
            "B                                B     B",
            "B                                B     B",
            "B                                B     B",
            "B                                B     B",
            "B         0000                BBBB     B",
            "B                   BBB          B     B",
            "BBB                       B      B     B",
            "B              BB                BB    B",
            "B              BB                      B",
            "B    B         BB         BB           B",
            "BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB",
    };

    /**
     * Game loop
     */

    public void run() {
        createGui();
        init();

        while (running) {
            long start = System.nanoTime();

            time /= 20;

            update(time);
            draw();
            display(image);
            clearScreen(graphics);

            long finish = System.nanoTime();

            time = (double) (finish - start) / 1_000_000;
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
        running = true;

        image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        graphics = (Graphics2D) image.getGraphics();

        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        entity = new Entity();
    }

    private void update(double time) {
        entity.update(time);
    }

    private void draw() {
        for (int i = 0; i < H_MAP; i++) {
            for (int j = 0; j < W_MAP; j++) {
                String row = tilemap[i];
                char tile = row.charAt(j);

                if (tile == 'B')
                    graphics.setColor(new Color(100, 100, 100, 255));

                if (tile == '0')
                    graphics.setColor(new Color(0, 255, 0, 255));

                if (tile == ' ')
                    continue;

                graphics.fillRect(j * 32, i * 32, 32, 32);
            }
        }

        entity.draw(graphics);
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
                running = false;

            if (keyCode == KeyEvent.VK_LEFT)
                entity.setLeft(true);

            if (keyCode == KeyEvent.VK_RIGHT)
                entity.setRight(true);

            if (keyCode == KeyEvent.VK_UP)
                entity.setJumping(true);

            if (keyCode == KeyEvent.VK_DOWN)
                entity.setSitting(true);
        }

        @Override
        public void keyReleased(KeyEvent e) {
            int keyCode = e.getKeyCode();

            if (keyCode == KeyEvent.VK_LEFT)
                entity.setLeft(false);

            if (keyCode == KeyEvent.VK_RIGHT)
                entity.setRight(false);

            if (keyCode == KeyEvent.VK_UP)
                entity.setJumping(false);

            if (keyCode == KeyEvent.VK_DOWN)
                entity.setSitting(false);
        }
    };
}
