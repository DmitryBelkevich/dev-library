package com.hard;

import com.hard.controllers.Editor;
import com.hard.models.Model;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

public class Main {
    public static void main(String[] args) {
        // load from file
        Model model = new Model();

        model.setX(0);
        model.setY(0);
        model.setW(100);
        model.setH(100);

        // controller
        Editor editor = new Editor(model);
        editor.run();
    }
}
