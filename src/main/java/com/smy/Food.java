package com.smy;

import java.awt.Color;
import java.util.Random;

public class Food {
    private Grid grid;
    private Tile tile;
    private Random random;
    private Color color = Color.red;

    public Food(Grid grid) {
        this.grid = grid;
        this.random = new Random();
        int randomX = random.nextInt(grid.getWidth() / grid.getTileSize());
        int randomY = random.nextInt(grid.getHeight() / grid.getTileSize());
        tile = new Tile(randomX, randomY);
    }

    public void randomizePosition() {
        tile.setX(random.nextInt(grid.getWidth() / grid.getTileSize()));
        tile.setY(random.nextInt(grid.getHeight() / grid.getTileSize()));
    }

    public Tile getTile() {
        return tile;
    }

    public Color getColor() {
        return color;
    }
}
