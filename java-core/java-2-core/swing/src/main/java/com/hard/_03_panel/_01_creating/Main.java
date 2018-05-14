package com.hard._03_panel._01_creating;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        MainFrame frame = new MainFrame();

        frame.create();
    }
}

class MainFrame {
    public void create() {
        JFrame frame = new JFrame("App");

        frame.setSize(640, 480);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        Panel panel = new Panel();
        panel.setPreferredSize(new Dimension(640, 480));
        frame.setContentPane(panel);

        frame.pack();
        frame.setVisible(true);
    }
}

class Panel extends JPanel {
}
