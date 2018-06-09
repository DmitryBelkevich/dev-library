package com.hard.my_chat.server.views;

import com.hard.my_chat.server.Server;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class FrameView extends View {
    private JTextArea textArea;

    public FrameView(Server server) {
        super(server);
    }

    @Override
    public void run() {
        createGui();

        new Thread(() -> {
            draw();
        }).start();
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

        textArea = new JTextArea(20, 50);
        textArea.setEditable(false);

        panel.add(textArea);

        frame.pack();
        frame.setVisible(true);

        /**
         * Listeners
         */

        frame.addWindowListener(new CloseWindowListener());
    }

    @Override
    public void getMessage(String str) {

    }

    @Override
    public void sendMessage(String str) {

    }

    public void draw() {
        while (true) {
            int clientsCount = server.clientsSize();
            textArea.setText(String.valueOf(clientsCount));

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private class CloseWindowListener extends WindowAdapter {
        @Override
        public void windowClosing(WindowEvent e) {
            server.setLaunched(false);
        }
    }
}
