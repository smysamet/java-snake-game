package com.smy;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.JPanel;

public class DrawManager extends JPanel {

    private Color backgroundColor = Color.black;
    private Color uiColor = Color.white;
    private Font uiTextFont;
    private Snake snake;
    private Food food;
    private Grid grid;

    public DrawManager(Snake snake, Food food, Grid grid) {
        this.snake = snake;
        this.food = food;
        this.grid = grid;
        setPreferredSize(new Dimension(grid.getWidth(), grid.getHeight()));
        setBackground(backgroundColor);
        this.uiTextFont = new Font("Arial", Font.BOLD, 16);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawUi(g);
        drawSnake(g);
        drawFood(g);
    }

    private void drawSnake(Graphics g) {
        Color snakeColor = snake.getColor();

        // head
        Tile snakeHead = snake.getHead();
        g.setColor(snakeColor);
        g.fillRect(
                snakeHead.getX() * grid.getTileSize(),
                snakeHead.getY() * grid.getTileSize(),
                grid.getTileSize(),
                grid.getTileSize());

        // body
        ArrayList<Tile> snakeBody = snake.getBody();
        for (int i = 0; i < snakeBody.size(); i++) {
            Tile snakePart = snakeBody.get(i);
            g.fillRect(
                    snakePart.getX() * grid.getTileSize(),
                    snakePart.getY() * grid.getTileSize(),
                    grid.getTileSize(),
                    grid.getTileSize());
        }
    }

    private void drawFood(Graphics g) {
        Tile foodTile = food.getTile();
        Color color = food.getColor();
        g.setColor(color);
        g.fillRect(
                foodTile.getX() * grid.getTileSize(),
                foodTile.getY() * grid.getTileSize(),
                grid.getTileSize(),
                grid.getTileSize());
    }

    private void drawUi(Graphics g) {
        g.setColor(uiColor);
        g.setFont(uiTextFont);

        if (snake.isAlive()) {
            g.drawString("Score: " + snake.getScore(), grid.getTileSize(), grid.getTileSize());

        } else {
            g.drawString("Game over, score: " + snake.getScore(), grid.getTileSize(), grid.getTileSize());
        }
    }
}
