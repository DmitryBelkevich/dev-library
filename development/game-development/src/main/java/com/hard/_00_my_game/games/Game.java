package com.hard._00_my_game.games;

import com.hard._00_my_game.GameFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Game {
    // window
    protected JFrame frame;

    // settings

    // graphics
    protected BufferedImage bufferedImage;
    protected Graphics graphics;

    public Game() {
        // window
        GameFrame gameFrame = new GameFrame();
        gameFrame.create();

        this.frame = gameFrame.getFrame();

        // graphics
        this.bufferedImage = new BufferedImage(GameFrame.WIDTH, GameFrame.HEIGHT, BufferedImage.TYPE_INT_RGB);
        this.graphics = bufferedImage.getGraphics();
    }

    public abstract void run();

    public abstract void update();

    public abstract void draw();
}
