package com.hard;

import javax.swing.*;

public class GameLauncher {
    public static void main(String[] args) {
        JFrame window = new JFrame("Dragon Tale");

        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setContentPane(new GamePanel());

        window.pack();
        window.setVisible(true);
    }
}
