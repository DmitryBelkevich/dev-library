package com.hard;

import com.hard.config.Screen;
import com.hard.entities.Entity;
import com.hard.entities.TileMap;
import com.hard.game_states.GameState;
import com.hard.game_states.Level1State;
import com.hard.maps.Maps;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

public class Game {
    private volatile boolean running;
    private volatile boolean pause;

    // screen
    private static final String TITLE = "Game";

    // physic
    public static final double GRAVITY = 1.2;

    // graphics
    private JPanel panel;
    private BufferedImage image;
    private Graphics2D graphics;

    // time
    private double time;

    // states
    private GameState gameState;

    /**
     * Game loop
     */

    public void run() {
        createGui();
        init();

        while (running) {
            while (pause) {
            }

            long start = System.nanoTime();

            time *= 0.045;

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

        frame.setSize(Screen.WIDTH, Screen.HEIGHT);

        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        panel = new JPanel();
        panel.setPreferredSize(new Dimension(Screen.WIDTH, Screen.HEIGHT));
        frame.setContentPane(panel);

        frame.pack();
        frame.setVisible(true);

        frame.addKeyListener(keyListener);
    }

    private void init() {
        running = true;

        image = new BufferedImage(Screen.WIDTH, Screen.HEIGHT, BufferedImage.TYPE_INT_RGB);
        graphics = (Graphics2D) image.getGraphics();

        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        gameState = new Level1State();
    }

    private void update(double time) {
        gameState.update(time);
    }

    private void draw() {
        gameState.draw(graphics);
    }

    private void display(BufferedImage image) {
        Graphics graphics = panel.getGraphics();
        graphics.drawImage(image, 0, 0, null);
        graphics.dispose();
    }

    private void clearScreen(Graphics graphics) {
        graphics.setColor(new Color(0, 0, 0, 255));
        graphics.fillRect(0, 0, Screen.WIDTH, Screen.HEIGHT);
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

            if (keyCode == KeyEvent.VK_P) {
                if (pause)
                    pause = false;
                else
                    pause = true;
            }

            gameState.keyPressed(e);
        }

        @Override
        public void keyReleased(KeyEvent e) {
            gameState.keyReleased(e);
        }
    };
}
