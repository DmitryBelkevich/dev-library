package com.hard._02_adding_components_and_listeners._02_android_style;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        MainFrame frame = new MainFrame();

        frame.onCreate();
    }
}

class R {
    public static class id {
        public static final int button1 = 1;
    }
}

/**
 * Initial context
 */

class InitialContext {
    public JComponent getComponent(int id) {
        switch (id) {
            case R.id.button1:
                JButton button = new JButton("Button1");
                return button;
        }

        return null;
    }
}

/**
 * Component locator
 */

class ComponentLocator {
    private InitialContext initialContext = new InitialContext();
    private Map<Integer, JComponent> cache = new HashMap<>();

    public JComponent getComponent(int id) {
        JComponent component = cache.get(id);

        if (component == null) {
            component = initialContext.getComponent(id);
            cache.put(id, component);
        }

        return component;
    }
}

/**
 * Frames
 */

class AppCompatFrame {
    private ComponentLocator componentLocator = new ComponentLocator();

    // 1.1 create frame
    protected JFrame frame = new JFrame("App");

    private JButton button1;

    public AppCompatFrame() {
        this.button1 = (JButton) componentLocator.getComponent(R.id.button1);

        /**
         * 1 frame:
         */

        // 1.2 window settings
        frame.setSize(640, 480);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        // 1.3 set layout
        frame.setLayout(new GridBagLayout());

        /**
         * 2 components:
         */

        // 2.2 components settings
        button1.setCursor(new Cursor(Cursor.HAND_CURSOR));    // add awt-components

        // 2.3 create layout Components

        /**
         * 4 add components:
         */

        // 4.1 add components (may with layout)
        frame.add(button1);
    }

    protected void onCreate() {

    }

    public JComponent findViewById(int id) {
        return button1;
    }
}

class MainFrame extends AppCompatFrame {
    // 2.1 create components (if need access to components from listeners)
    private JButton button1;

    @Override
    protected void onCreate() {
        button1 = (JButton) findViewById(R.id.button1);

        /**
         * 5 add listeners:
         */

        // 5.1 add Listeners (may use Anonymous class, lambdas)
        button1.addActionListener(new Button1ActionListener());
        frame.addWindowListener(new CloseWindowListener());

        // 1.4 other frame settings
        frame.setVisible(true);
        //frame.pack();
    }

    /**
     * 3 listeners
     */

    // 3.1 create listeners (may use any class: Inner Class, Nested Class)
    private class Button1ActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            System.out.println("Hello World");
            //event.getSource();	// возвращает объект
        }
    }

    private class CloseWindowListener extends WindowAdapter {
        @Override
        public void windowClosing(WindowEvent e) {
            System.out.println("Window is closing");
        }
    }
}
