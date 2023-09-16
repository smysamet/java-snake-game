package com.smy;

public class Grid {
    private int tileSize;
    private int width;
    private int height;

    public Grid(int tileSize, int width, int height) {
        this.tileSize = tileSize;
        this.width = width;
        this.height = height;
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
}
