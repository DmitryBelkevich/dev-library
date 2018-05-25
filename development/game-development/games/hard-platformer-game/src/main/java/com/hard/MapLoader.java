package com.hard;

public class MapLoader {
    private int h;
    private int w;

    public char[][] loadStringArray(String[] strings) {
        // create
        h = strings.length;
        w = strings[0].length();

        char[][] charsTilemap = new char[w][h];

        // fill
        for (int i = 0; i < strings.length; i++) {
            String row = strings[i];
            charsTilemap[i] = row.toCharArray();
        }

        return charsTilemap;
    }

    public char[][] loadTxtFile(String path) {
        // TODO
        return null;
    }

    public char[][] loadTmxFile(String path) {
        // TODO
        return null;
    }

    public int getH() {
        return h;
    }

    public int getW() {
        return w;
    }
}
