package com.hard._03_panel._02_drawing._02_in_thread;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

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
        frame.setContentPane(panel);

//        frame.pack();
        frame.setVisible(true);

        Thread thread = new Thread(() -> {
            BufferedImage bufferedImage = new BufferedImage(200, 100, BufferedImage.TYPE_INT_RGB);
            Graphics2D graphics2D = (Graphics2D) bufferedImage.getGraphics();

            graphics2D.drawString("Hello World", 0, 10);

            Graphics graphics = frame.getGraphics();
            graphics.drawImage(bufferedImage, 50, 50, null);
            graphics.dispose();
        });
        thread.start();
    }
}

class Panel extends JPanel {
}
