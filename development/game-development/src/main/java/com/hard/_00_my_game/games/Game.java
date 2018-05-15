package com.hard._00_my_game.games;

import com.hard._00_my_game.GameFrame;
import com.hard._00_my_game.entities.Background;
import com.hard._00_my_game.entities.Entity;
import com.hard._00_my_game.entities.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Game {
    // window
    private JFrame frame;

    // settings

    // graphics
    private BufferedImage bufferedImage;
    private Graphics graphics;

    // entities
    private Entity background;
    private Entity entity;

    public Game() {
        // window
        GameFrame gameFrame = new GameFrame();
        gameFrame.create();

        this.frame = gameFrame.getFrame();

        // graphics
        this.bufferedImage = new BufferedImage(GameFrame.WIDTH, GameFrame.HEIGHT, BufferedImage.TYPE_INT_RGB);
        this.graphics = bufferedImage.getGraphics();

        // entities
        this.entity = new Player(0, 0);
        this.background = new Background(0, 0);
    }

    public void run() {
        while (true) {
            update();
            draw(graphics);
            display(frame);

            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void update() {
        background.update();
        entity.update();
    }

    public void draw(Graphics graphics) {
        background.draw(graphics);
        entity.draw(graphics);
    }

    public void display(JFrame frame) {
        Graphics graphics = frame.getGraphics();
        graphics.drawImage(bufferedImage, 0, 0, null);
        graphics.dispose();
    }
}
