package com.hard._00_my_game;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Main extends JPanel {
    public static void main(String[] args) {
        Game game = new Game();
        game.run();
    }
}

class GameFrame {
    public static int WIDTH = 640;
    public static int HEIGHT = 480;

    private JFrame frame = new JFrame("App");

    public void create() {
        frame.setSize(WIDTH, HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);

        frame.setVisible(true);
    }

    public JFrame getFrame() {
        return frame;
    }
}

class Game {
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

abstract class Entity {
    protected int x;
    protected int y;

    public Entity(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public abstract void update();

    public abstract void draw(Graphics graphics);
}

class Player extends Entity {
    public Player(int x, int y) {
        super(x, y);
    }

    @Override
    public void update() {
        x++;
        y++;
    }

    @Override
    public void draw(Graphics graphics) {
        graphics.setColor(Color.RED);
        graphics.drawString("Hello World", x, y);
    }
}

class Background extends Entity {
    public Background(int x, int y) {
        super(x, y);
    }

    @Override
    public void update() {

    }

    @Override
    public void draw(Graphics graphics) {
        graphics.setColor(new Color(0, 0, 0));
        graphics.fillRect(x, y, GameFrame.WIDTH, GameFrame.HEIGHT);
    }
}
