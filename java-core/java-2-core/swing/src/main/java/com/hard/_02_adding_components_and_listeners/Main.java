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
    public void create() {
        /**
         * Frame:
         */

        // 1. create frame
        JFrame frame = new JFrame("App");

        // 2. Window settings
        frame.setSize(640, 480);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        // 3. set layout
        frame.setLayout(new GridBagLayout());

        /**
         * Components:
         */

        /**
         * ADD COMPONENTS AND LISTENERS:
         */

        // other frame settings
        frame.setVisible(true);
        //frame.pack();
    }

    /**
     * Listeners
     */
}
