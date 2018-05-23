package com.hard._00_my_template._02;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Game game = new Game();
        game.run();
    }
}

class Game {
    private volatile boolean running;
    private JPanel panel;

    private static final String TITLE = "Game";
    private static final double SCALE = 1;
    public static final int WIDTH = (int) (640 * SCALE);
    public static final int HEIGHT = (int) (480 * SCALE);

    // graphics
    private BufferedImage image;
    private Graphics2D graphics;

    // entity
    private Entity entity;

    // time
    private double time;

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
        entity.draw(graphics);

        drawConsole();
    }

    private void drawConsole() {
        double x = entity.getX();
        double y = entity.getY();

        double dx = entity.getDx();
        double dy = entity.getDy();

        double speed = entity.getSpeed();

        boolean left = entity.isLeft();
        boolean right = entity.isRight();
        boolean up = entity.isUp();
        boolean down = entity.isDown();

        int step = 20;

        graphics.setColor(new Color(0, 255, 0, 255));

//        if (x != 0) graphics.setColor(new Color(225, 0, 0, 255));
        graphics.drawString("x=" + x, 50, 1 * step);
//        graphics.setColor(new Color(0, 255, 0, 255));

//        if (y != 0) graphics.setColor(new Color(225, 0, 0, 255));
        graphics.drawString("y=" + y, 50, 2 * step);
//        graphics.setColor(new Color(0, 255, 0, 255));

        if (dx != 0) graphics.setColor(new Color(225, 0, 0, 255));
        graphics.drawString("dx=" + dx, 50, 3 * step);
        graphics.setColor(new Color(0, 255, 0, 255));

        if (dy != 0) graphics.setColor(new Color(225, 0, 0, 255));
        graphics.drawString("dy=" + dy, 50, 4 * step);
        graphics.setColor(new Color(0, 255, 0, 255));

        if (speed != 0) graphics.setColor(new Color(225, 0, 0, 255));
        graphics.drawString("speed=" + speed, 50, 5 * step);
        graphics.setColor(new Color(0, 255, 0, 255));


        if (left) graphics.setColor(new Color(225, 0, 0, 255));
        graphics.drawString("left=" + left, 50, 7 * step);
        graphics.setColor(new Color(0, 255, 0, 255));

        if (right) graphics.setColor(new Color(225, 0, 0, 255));
        graphics.drawString("right=" + right, 50, 8 * step);
        graphics.setColor(new Color(0, 255, 0, 255));

        if (up) graphics.setColor(new Color(225, 0, 0, 255));
        graphics.drawString("up=" + up, 50, 9 * step);
        graphics.setColor(new Color(0, 255, 0, 255));

        if (down) graphics.setColor(new Color(225, 0, 0, 255));
        graphics.drawString("down=" + down, 50, 10 * step);
        graphics.setColor(new Color(0, 255, 0, 255));
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
                entity.setUp(true);

            if (keyCode == KeyEvent.VK_DOWN)
                entity.setDown(true);
        }

        @Override
        public void keyReleased(KeyEvent e) {
            int keyCode = e.getKeyCode();

            if (keyCode == KeyEvent.VK_LEFT)
                entity.setLeft(false);

            if (keyCode == KeyEvent.VK_RIGHT)
                entity.setRight(false);

            if (keyCode == KeyEvent.VK_UP)
                entity.setUp(false);

            if (keyCode == KeyEvent.VK_DOWN)
                entity.setDown(false);
        }
    };
}

class Entity {
    private int w = 64;
    private int h = 64;

    private double x = Game.WIDTH / 2 - w / 2;
    private double y = Game.HEIGHT / 2 - h / 2;

    private double speed = 0;

    private double dx = 0;
    private double dy = 0;

    // moving
    private boolean left = false;
    private boolean right = false;
    private boolean up = false;
    private boolean down = false;

    // animation
    private AnimationManager animationManager;

    public Entity() {
        animationManager = new AnimationManager();
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public double getDx() {
        return dx;
    }

    public void setDx(double dx) {
        this.dx = dx;
    }

    public double getDy() {
        return dy;
    }

    public void setDy(double dy) {
        this.dy = dy;
    }

    public boolean isLeft() {
        return left;
    }

    public void setLeft(boolean left) {
        this.left = left;

        speed = 0;
        dx = speed;
    }

    public boolean isRight() {
        return right;
    }

    public void setRight(boolean right) {
        this.right = right;

        speed = 0;
        dx = speed;
    }

    public boolean isUp() {
        return up;
    }

    public void setUp(boolean up) {
        this.up = up;

        speed = 0;
        dy = speed;
    }

    public boolean isDown() {
        return down;
    }

    public void setDown(boolean down) {
        this.down = down;

        speed = 0;
        dy = speed;
    }

    public void update(double time) {
        /**
         * check moving
         */

        if (left) {
            moveToLeft();
        }

        if (right) {
            moveToRight();
        }

        if (up) {
            moveToUp();
        }

        if (down) {
            moveToDown();
        }

        /**
         * moving
         */

        x += dx * time;
        y += dy * time;

        /**
         * check collision
         */

        if (x < 0)
            x = 0;

        if (y < 0)
            y = 0;

        if (x > Game.WIDTH - w)
            x = Game.WIDTH - w;

        if (y > Game.HEIGHT - h)
            y = Game.HEIGHT - h;

        /**
         * stop moving
         */

        /**
         * animation
         */
        animationManager.update(time);
    }

    public void draw(Graphics graphics) {
        // drawing entity (circle)
        graphics.setColor(new Color(255, 0, 0, 255));
        graphics.fillOval((int) x, (int) y, w, h);

        // drawing entity (sprite)
        animationManager.draw(graphics, x, y, w, h);
    }

    public void moveToLeft() {
        speed = 5;
        dx = -speed;
    }

    public void moveToRight() {
        speed = 5;
        dx = speed;
    }

    public void moveToUp() {
        speed = 5;
        dy = -speed;
    }

    public void moveToDown() {
        speed = 5;
        dy = speed;
    }
}

class AnimationManager {
    private List<Animation> animations;
    private Animation currentAnimation;

    public AnimationManager() {
        animations = new ArrayList<>();

        Animation animation = new Animation();
        animations.add(animation);

        currentAnimation = animation;
    }

    public void update(double time) {
        currentAnimation.update(time);
    }

    public void draw(Graphics graphics, double x, double y, int w, int h) {
        currentAnimation.draw(graphics, x, y, w, h);
    }
}

class Animation {
    private List<BufferedImage> frames;

    private double currentFrame = 0;
    private double speedFrame = 0.145;

    // params
    private boolean playing;
    private boolean looped;
    private boolean flipped;

    public Animation() {
        Class<?> clazz = this.getClass();
        InputStream inputStream = clazz.getResourceAsStream("/player.png");

        BufferedImage sprite = null;
        try {
            sprite = ImageIO.read(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        frames = new ArrayList<>();

        int wSprite = 95;
        int hSprite = 75;

        BufferedImage frame1 = sprite.getSubimage(0 + wSprite * 0, 220, wSprite, hSprite);
        BufferedImage frame2 = sprite.getSubimage(0 + wSprite * 1, 220, wSprite, hSprite);
        BufferedImage frame3 = sprite.getSubimage(0 + wSprite * 2, 220, wSprite, hSprite);

        frames.add(frame1);
        frames.add(frame2);
        frames.add(frame3);
    }

    public boolean isPlaying() {
        return playing;
    }

    public void setPlaying(boolean playing) {
        this.playing = playing;
    }

    public boolean isLooped() {
        return looped;
    }

    public void setLooped(boolean looped) {
        this.looped = looped;
    }

    public boolean isFlipped() {
        return flipped;
    }

    public void setFlipped(boolean flipped) {
        this.flipped = flipped;
    }

    public void update(double time) {
        currentFrame += speedFrame * time;
        if (currentFrame >= frames.size())
            currentFrame = 0;
    }

    public void draw(Graphics graphics, double x, double y, int w, int h) {
        BufferedImage frame = frames.get((int) currentFrame);

        graphics.drawImage(frame, (int) x, (int) y, w, h, null);
    }
}
