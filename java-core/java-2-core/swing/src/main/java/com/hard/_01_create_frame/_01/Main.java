package com.hard._01_create_frame._01;

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

        frame.setLayout(new GridBagLayout());

        frame.setVisible(true);
    }
}
