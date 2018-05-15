package com.hard._00_my_game.repository;

import java.util.ArrayList;
import java.util.List;

public class GameStateSaver {
    private List<File> files = new ArrayList<>();

    public void save(File file) {
        files.add(file);
    }

    public File getFile(int i) {
        return files.get(i);
    }
}
