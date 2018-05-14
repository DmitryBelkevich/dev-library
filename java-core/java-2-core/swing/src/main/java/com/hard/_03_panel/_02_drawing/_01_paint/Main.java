package com.hard._03_panel._02_drawing._01_paint;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame();

        frame.setSize(640,480);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        frame.setContentPane(new Panel());

//        frame.pack();
        frame.setVisible(true);
    }
}

class Panel extends JPanel {
    @Override
    public void paint(Graphics graphics) {
        graphics.drawString("Hello World", 0, 10);
    }
}
