package com.hard._00_my_game;

import com.hard._00_my_game.games.Game;

import javax.swing.*;

public class Main extends JPanel {
    public static void main(String[] args) {
        Game game = new Game();
        game.run();
    }
}
