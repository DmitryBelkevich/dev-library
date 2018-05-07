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
        public static final int main_frame = 1;

        public static final int button1 = 2;
    }
}

/**
 * Initial context
 */

class InitialContext {
    public JFrame getFrame(int id) {
        switch (id) {
            case R.id.main_frame:
                JFrame frame = new JFrame("App");

                frame.setSize(640, 480);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setLocationRelativeTo(null);

                frame.setLayout(new GridBagLayout());

                return frame;
        }

        return null;
    }

    public JComponent getComponent(int id) {
        switch (id) {
            case R.id.button1:
                JButton button = new JButton("Button1");
                button.setCursor(new Cursor(Cursor.HAND_CURSOR));
                return button;
        }

        return null;
    }
}

/**
 * Component locator
 */

class Locator {
    private InitialContext initialContext = new InitialContext();
    private Map<Integer, JFrame> frames = new HashMap<>();
    private Map<Integer, JComponent> components = new HashMap<>();

    public JFrame getFrame(int id) {
        JFrame frame = frames.get(id);

        if (frame == null) {
            frame = initialContext.getFrame(id);

            JButton button1 = (JButton) getComponent(R.id.button1);
            frame.add(button1);

            frames.put(id, frame);
        }

        return frame;
    }

    public JComponent getComponent(int id) {
        JComponent component = components.get(id);

        if (component == null) {
            component = initialContext.getComponent(id);
            components.put(id, component);
        }

        return component;
    }
}

/**
 * Frames
 */

class AppCompatFrame {
    private Locator locator = new Locator();

    protected JFrame frame;

    public AppCompatFrame() {
        frame = locator.getFrame(R.id.main_frame);

        frame.setVisible(true);
        //frame.pack();
    }

    protected void onCreate() {

    }

    public JComponent findComponentById(int id) {
        return locator.getComponent(id);
    }
}

class MainFrame extends AppCompatFrame {
    private JButton button1;

    @Override
    protected void onCreate() {
        findElements();
        addListeners();
    }

    public void findElements() {
        button1 = (JButton) findComponentById(R.id.button1);
    }

    public void addListeners() {
        button1.addActionListener(new Button1ActionListener());
        frame.addWindowListener(new CloseWindowListener());
    }

    private class Button1ActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            System.out.println("Hello World");
        }
    }

    private class CloseWindowListener extends WindowAdapter {
        @Override
        public void windowClosing(WindowEvent e) {
            System.out.println("Window is closing");
        }
    }
}
