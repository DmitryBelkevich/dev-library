package com.hard;

import javax.swing.*;

public class GameLauncher {
    public static void main(String[] args) {
        JFrame window = new JFrame("Artifact");

        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);

        window.add(new GamePanel());

        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }
}
