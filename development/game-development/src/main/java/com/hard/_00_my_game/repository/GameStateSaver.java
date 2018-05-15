package com.hard._00_my_game.repository;

public class GameStateSaver {
    private File file;

    public void save(File file) {
        this.file = file;
    }

    public File load() {
        return file;
    }
}
