package com.hard._00_my_game;

import javax.swing.*;

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
}
