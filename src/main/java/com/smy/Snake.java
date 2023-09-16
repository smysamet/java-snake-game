package com.smy;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

public class Snake {

    private Tile head;
    private ArrayList<Tile> body;
    private int velocityX;
    private int velocityY;
    private Color color = Color.green;
    private Grid grid;
    private boolean isAlive;

    public Snake(Grid grid) {
        this.grid = grid;
        setHeadToRandomPosition();
        body = new ArrayList<>();
        isAlive = true;
    }

    public void move() {
        if (!isAlive) {
            return;
        }

        // snake body
        for (int i = body.size() - 1; i >= 0; i--) {
            Tile snakePart = body.get(i);

            if (i == 0) {
                snakePart.setX(head.getX());
                snakePart.setY(head.getY());
            } else {
                Tile previousSneakPart = body.get(i - 1);
                snakePart.setX(previousSneakPart.getX());
                snakePart.setY(previousSneakPart.getY());
            }
        }

        // snake head
        head.setX(head.getX() + velocityX);
        head.setY(head.getY() + velocityY);
    }

    private void setHeadToRandomPosition() {
        Random random = new Random();
        int randomX = random.nextInt(grid.getWidth() / grid.getTileSize());
        int randomY = random.nextInt(grid.getHeight() / grid.getTileSize());
        head = new Tile(randomX, randomY);
    }

    public Color getColor() {
        return color;
    }

    public ArrayList<Tile> getBody() {
        return body;
    }

    public Tile getHead() {
        return head;
    }

    public void setVelocity(int x, int y) {
        velocityX = x;
        velocityY = y;
    }

    public int getVelocityX() {
        return this.velocityX;
    }

    public int getVelocityY() {
        return this.velocityY;
    }

    public int[] getWorldLocation() {
        return new int[] { head.getX() * grid.getTileSize(), head.getY() * grid.getTileSize() };
    }

    public int getScore() {
        return body.size();
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setIsAlive(boolean isAlive) {
        this.isAlive = isAlive;
    }

}
