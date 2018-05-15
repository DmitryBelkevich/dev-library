package com.hard._00_my_game.games.game1;

import com.hard._00_my_game.GameFrame;
import com.hard._00_my_game.games.game1.entities.Background;
import com.hard._00_my_game.games.game1.entities.Entity;
import com.hard._00_my_game.games.game1.entities.Player;
import com.hard._00_my_game.games.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Game1 extends Game {
    // window
    private JFrame frame;

    // settings

    // graphics
    private BufferedImage bufferedImage;
    private Graphics graphics;

    // entities
    private Entity background;
    private Entity player;

    public Game1() {
        // window
        GameFrame gameFrame = new GameFrame();
        gameFrame.create();

        this.frame = gameFrame.getFrame();

        // graphics
        this.bufferedImage = new BufferedImage(GameFrame.WIDTH, GameFrame.HEIGHT, BufferedImage.TYPE_INT_RGB);
        this.graphics = bufferedImage.getGraphics();

        // entities
        this.player = new Player(0, 0);
        this.background = new Background(0, 0);
    }

    @Override
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

    @Override
    public void update() {
        background.update();
        player.update();
    }

    @Override
    public void draw(Graphics graphics) {
        background.draw(graphics);
        player.draw(graphics);
    }

    public void display(JFrame frame) {
        Graphics graphics = frame.getGraphics();
        graphics.drawImage(bufferedImage, 0, 0, null);
        graphics.dispose();
    }
}
