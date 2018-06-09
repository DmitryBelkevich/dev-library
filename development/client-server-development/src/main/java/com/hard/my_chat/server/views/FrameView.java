package com.hard.my_chat.server.views;

import javax.swing.*;
import java.awt.*;

public class FrameView implements View {
    @Override
    public void run() {
        createGui();
    }

    public void createGui() {
        JFrame frame = new JFrame("Server");

        frame.setSize(640, 480);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(640 - 10, 480));
        frame.setContentPane(panel);

        /**
         * components
         */

        JTextArea textArea = new JTextArea(20, 50);
        textArea.setEditable(false);

        panel.add(textArea);

        frame.pack();
        frame.setVisible(true);
    }
}
