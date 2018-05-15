package com.hard._00_my_game.games;

import com.hard._00_my_game.GameFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

public abstract class Game {
    // window
    protected JFrame frame;

    // settings
    protected int speed;

    // states
    protected GameStateManager gameStateManager;

    // graphics
    protected BufferedImage bufferedImage;
    protected Graphics graphics;

    public Game() {
        // window
        GameFrame gameFrame = new GameFrame();
        gameFrame.create();

        gameFrame.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                int keyCode = e.getKeyCode();
                gameStateManager.keyPressed(keyCode);
            }

            @Override
            public void keyReleased(KeyEvent e) {
                int keyCode = e.getKeyCode();
                gameStateManager.keyReleased(keyCode);
            }
        });

        this.frame = gameFrame.getFrame();

        // settings
        this.speed = 1000;

        // states
        this.gameStateManager = new GameStateManager();

        // graphics
        this.bufferedImage = new BufferedImage(GameFrame.WIDTH, GameFrame.HEIGHT, BufferedImage.TYPE_INT_RGB);
        this.graphics = bufferedImage.getGraphics();
    }

    public abstract void run();

    public abstract void update();

    public abstract void draw();
}
