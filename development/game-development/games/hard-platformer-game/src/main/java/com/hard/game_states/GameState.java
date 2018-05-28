package com.hard.game_states;

import java.awt.*;
import java.awt.event.KeyEvent;

public interface GameState {
    void keyPressed(KeyEvent e);

    void keyReleased(KeyEvent e);

    void update(double time);

    void draw(Graphics graphics);
}
