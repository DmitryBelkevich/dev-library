package com.hard._01_create_frame._02;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        new MainFrame("App", new Dimension(640, 480));
    }
}

class MainFrame extends JFrame {
    public MainFrame(String title, Dimension dimension) {
        setTitle(title);
        setSize(dimension);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setLayout(new GridBagLayout());

        setVisible(true);
    }
}
