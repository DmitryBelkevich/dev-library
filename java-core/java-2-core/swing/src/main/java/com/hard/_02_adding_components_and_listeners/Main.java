package com.hard._02_adding_components_and_listeners;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        MainFrame frame = new MainFrame();

        frame.create();
    }
}

class MainFrame {
    // 2.1 create Components (if need access to components from listeners)
    private JButton button1 = new JButton("Button1");

    public void create() {
        /**
         * 1 Frame:
         */

        // 1.1 create frame
        JFrame frame = new JFrame("App");

        // 1.2 Window settings
        frame.setSize(640, 480);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        // 1.3 set layout
        frame.setLayout(new GridBagLayout());

        /**
         * 2 Components:
         */

        // 2.2 Components settings
        button1.setCursor(new Cursor(Cursor.HAND_CURSOR));	// add awt-components

        // 2.3 create layout Components

        /**
         * 3 ADD COMPONENTS AND LISTENERS:
         */

        // 1.4 other frame settings
        frame.setVisible(true);
        //frame.pack();
    }

    /**
     * 4 Listeners
     */
}
