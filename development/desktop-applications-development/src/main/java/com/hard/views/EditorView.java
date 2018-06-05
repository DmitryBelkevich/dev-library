package com.hard.views;

import com.hard.controllers.Editor;
import com.hard.models.Circle;
import com.hard.models.Rectangle;
import com.hard.models.Shape;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.util.Collection;

public class EditorView {
    /**
     * Gui
     */

    private JPanel panel;

    private Graphics2D panelGraphics;
    private BufferedImage canvas;
    private Graphics2D canvasGraphics;

    static class Canvas {
        static int x = 0;
        static int y = 0;
        static int w = 640;
        static int h = 480;
    }

    static class Camera {
        static int x = 0;
        static int y = 0;
        static int w = 640;
        static int h = 480;
    }

    /**
     * Controller
     */

    private Editor editor;

    /**
     * Model
     */

    private Collection<Shape> shapes;

    public EditorView(Editor editor, Collection<Shape> shapes) {
        this.editor = editor;
        this.shapes = shapes;
    }

    public void run() {
        createGui();
        initGraphic();
        initListeners();

        new Thread(() -> {
            while (true) {
                draw();
            }
        }).start();
    }

    public void createGui() {
        JFrame frame = new JFrame();

        frame.setSize(640, 480);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel = new JPanel();
        panel.setPreferredSize(new Dimension(640, 480));
        frame.add(panel);

        frame.pack();
        frame.setVisible(true);
    }

    public void initGraphic() {
        panelGraphics = (Graphics2D) panel.getGraphics();
        panelGraphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        panelGraphics.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        // init canvas
        canvas = new BufferedImage(EditorView.Canvas.w, EditorView.Canvas.h, BufferedImage.TYPE_INT_RGB);

        canvasGraphics = (Graphics2D) canvas.getGraphics();
        canvasGraphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        canvasGraphics.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
    }

    public void initListeners() {
        panel.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
                editor.mousePressed(e);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                editor.mouseReleased(e);
            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

        panel.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseMoved(MouseEvent e) {
                editor.mouseMoved(e);
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                editor.mouseDragged(e);
            }
        });
    }

    public void draw() {
        clearCanvas();
        drawCanvas();
        drawCanvasToPanel();
    }

    public void clearCanvas() {
        canvasGraphics.setColor(new Color(0, 0, 0, 255));
        canvasGraphics.fillRect(EditorView.Canvas.x, EditorView.Canvas.y, EditorView.Canvas.w, EditorView.Canvas.h);
    }

    public void drawCanvas() {
        for (Shape shape : shapes) {
            shape.draw(canvasGraphics);
        }
    }

    public void drawCanvasToPanel() {
        panelGraphics.drawImage(canvas, EditorView.Camera.x, EditorView.Camera.y, EditorView.Camera.w, EditorView.Camera.h, null);
    }
}
