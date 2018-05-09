package com.hard._03_panel;

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

        frame.setContentPane(new Panel());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

//        frame.setLayout(new GridBagLayout());

        frame.setVisible(true);
        frame.pack();
    }
}

class Panel extends JPanel {
    public Panel() {
        Dimension dimension = new Dimension(640, 480);
        setPreferredSize(dimension);
    }
}
