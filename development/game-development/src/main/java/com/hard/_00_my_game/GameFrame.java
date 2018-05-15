package com.hard._00_my_game;

import com.hard._00_my_game.games.GameStateManager;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameFrame {
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

    public void addKeyListener(GameStateManager gameStateManager) {
        frame.addKeyListener(new KeyListener() {
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
    }
}
