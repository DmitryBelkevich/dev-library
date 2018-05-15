package com.hard._00_my_game.games;

import com.hard._00_my_game.GameFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Game {
    // window
    protected GameFrame gameFrame;
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
        gameFrame = new GameFrame();
        gameFrame.create();

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
