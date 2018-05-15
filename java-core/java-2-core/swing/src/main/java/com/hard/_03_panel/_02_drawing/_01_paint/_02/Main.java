package com.hard._03_panel._02_drawing._01_paint._02;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame();

        frame.setSize(640, 480);
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
        BufferedImage bufferedImage = new BufferedImage(200, 100, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics2D = (Graphics2D) bufferedImage.getGraphics();

        graphics2D.drawString("Hello World", 0, 10);

        graphics.drawImage(bufferedImage, 50, 50, null);
        graphics.dispose();
    }
}
