package com.hard.templates._02;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Main {
    public static void main(String[] args) {
        JFrame window = new JFrame("platformer game");

        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);

        window.setContentPane(new GamePanel());

        window.setVisible(true);
        window.pack();
    }
}

class GamePanel extends JPanel implements Runnable {
    public static final int WIDTH = 320;
    public static final int HEIGHT = 240;
    public static final int SCALE = 1;

    private Thread thread;
    private boolean isRunning;

    private BufferedImage image;
    private Graphics2D graphics;

    public GamePanel() {
        super();
        setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
        setFocusable(true);
        requestFocus();
    }

    @Override
    public void addNotify() {
        super.addNotify();

        if (thread == null) {
            thread = new Thread(this);

//            addKeyListener(this);

            thread.start();
        }
    }

    @Override
    public void run() {
        isRunning = true;

        image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        graphics = (Graphics2D) image.getGraphics();

        while (isRunning) {
            gameUpdate();
            gameRender();
            gameDraw();
        }
    }

    private void gameUpdate() {

    }

    private void gameRender() {
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, WIDTH, HEIGHT);
        graphics.setColor(Color.BLACK);
        graphics.drawString("Test String", 100, 100);
    }

    private void gameDraw() {
        Graphics graphics = this.getGraphics();
        graphics.drawImage(image, 0, 0, null);
        graphics.dispose();
    }
}
