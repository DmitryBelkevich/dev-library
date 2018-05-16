package com.hard.tiles;

import com.hard.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileMap {
    // position
    private double x;
    private double y;

    // bounds
    private int xMin;
    private int yMin;
    private int xMax;
    private int yMax;

    private double tween;

    // map
    private int[][] map;
    private int tileSize;
    private int numRows;
    private int numCols;
    private int width;
    private int height;

    // tileset
    private BufferedImage tileset;
    private int numTilesAcross;
    private Tile[][] tiles;

    // drawing
    private int rowOffset;
    private int colOffset;
    private int numRowsToDraw;
    private int numColsToDraw;

    public TileMap(int tileSize) {
        this.tileSize = tileSize;
        numRowsToDraw = GamePanel.HEIGHT / tileSize + 2;
        numColsToDraw = GamePanel.WIDTH / tileSize + 2;
        tween = 0.07;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setTween(double tween) {
        this.tween = tween;
    }

    public int getTileSize() {
        return tileSize;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void loadTiles(String path) {
        Class<?> clazz = this.getClass();
        InputStream inputStream = clazz.getResourceAsStream(path);
        try {
            tileset = ImageIO.read(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        numTilesAcross = tileset.getWidth() / tileSize;
        tiles = new Tile[2][numTilesAcross];

        BufferedImage subImage;
        for (int col = 0; col < numTilesAcross; col++) {
            subImage = tileset.getSubimage(col * tileSize, 0, tileSize, tileSize);
            tiles[0][col] = new Tile(subImage, Tile.NORMAL);

            subImage = tileset.getSubimage(col * tileSize, tileSize, tileSize, tileSize);
            tiles[1][col] = new Tile(subImage, Tile.BLOCKED);
        }
    }

    public void loadMap(String path) {
        Class<?> clazz = this.getClass();
        InputStream inputStream = clazz.getResourceAsStream(path);
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

        try {
            numCols = Integer.parseInt(bufferedReader.readLine());
            numRows = Integer.parseInt(bufferedReader.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }

        map = new int[numRows][numCols];
        width = numCols * tileSize;
        height = numRows * tileSize;

        xMin = GamePanel.WIDTH - width;
        xMax = 0;
        yMin = GamePanel.HEIGHT - height;
        yMax = 0;

        String delims = "\\s+";
        for (int row = 0; row < numRows; row++) {
            String line = null;
            try {
                line = bufferedReader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            String[] tokens = line.split(delims);
            for (int col = 0; col < numCols; col++)
                map[row][col] = Integer.parseInt(tokens[col]);
        }
    }

    public int getType(int row, int col) {
        int rc = map[row][col];
        int r = rc / numTilesAcross;
        int c = rc % numTilesAcross;

        return tiles[r][c].getType();
    }

    public void setPosition(double x, double y) {
        this.x += (x - this.x) * tween;
        this.y += (y - this.y) * tween;

        fixBounds();

        colOffset = (int) -this.x / tileSize;
        rowOffset = (int) -this.y / tileSize;
    }

    private void fixBounds() {
        if (x < xMin)
            x = xMin;

        if (y < yMin)
            y = yMin;

        if (x > xMax)
            x = xMax;

        if (y > yMax)
            y = yMax;
    }

    public void draw(Graphics2D graphics) {
        for (int row = rowOffset; row < rowOffset + numRowsToDraw; row++) {
            if (row >= numRows)
                break;

            for (int col = colOffset; col < colOffset + numColsToDraw; col++) {
                if (col >= numCols)
                    break;

                if (map[row][col] == 0)
                    continue;

                int rc = map[row][col];
                int r = rc / numTilesAcross;
                int c = rc % numTilesAcross;

                graphics.drawImage(tiles[r][c].getImage(), (int) x + col * tileSize, (int) y + row * tileSize, null);
            }
        }
    }
}
