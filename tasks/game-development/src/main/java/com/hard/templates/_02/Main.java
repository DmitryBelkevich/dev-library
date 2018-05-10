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

    private int fps = 30;
    private double averageFps;

    private long targetTime = 1000 / fps;

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

        long totalTime = 0;

        int frameCount = 0;
        int maxFrameCount = 30;

        while (isRunning) {
            long startTime = System.nanoTime();

            gameUpdate();
            gameRender();
            gameDraw();

            long urdTimeMills = (System.nanoTime() - startTime) / 1000000;

            long waitingTime = targetTime - urdTimeMills;
            if (waitingTime < 0)
                waitingTime = 0;

            try {
                Thread.sleep(waitingTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            totalTime += System.nanoTime() - startTime;
            frameCount++;

            if (frameCount == maxFrameCount) {
                averageFps = 1000.0 / ((totalTime / frameCount) / 1000000);
                frameCount = 0;
                totalTime = 0;
            }
        }
    }

    private void gameUpdate() {

    }

    private void gameRender() {
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, WIDTH, HEIGHT);
        graphics.setColor(Color.BLACK);
        graphics.drawString("FPS: " + averageFps, 10, 10);
    }

    private void gameDraw() {
        Graphics graphics = this.getGraphics();
        graphics.drawImage(image, 0, 0, null);
        graphics.dispose();
    }
}
