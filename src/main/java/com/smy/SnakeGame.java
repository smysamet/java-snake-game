package com.smy;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.Timer;

public class SnakeGame implements ActionListener {
    private int boardWidth;
    private int boardHeight;
    private int tileSize = 25;
    private JFrame jFrame;
    private Timer timer;
    private Grid grid;

    private Snake snake;
    private Food food;
    private DrawManager drawManager;

    private boolean isGameOver;

    public SnakeGame(int boardWidth, int boardHeight, int tileSize, JFrame jFrame) {
        this.boardWidth = boardWidth;
        this.boardHeight = boardHeight;
        this.tileSize = tileSize;
        this.jFrame = jFrame;

        this.timer = new Timer(100, this);
        this.grid = new Grid(this.tileSize, this.boardWidth, this.boardHeight);

        this.snake = new Snake(grid);
        this.food = new Food(grid);
        this.drawManager = new DrawManager(snake, food, grid);
        InputManager inputManager = new InputManager(snake, grid);
        this.jFrame.add(drawManager);
        drawManager.add(inputManager);
        this.jFrame.pack();
        inputManager.requestFocus();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (isGameOver) {
            timer.stop();
            return;
        }

        checkCollision();
        snake.move();
        drawManager.repaint();
    }

    public void start() {
        timer.start();
    }

    private boolean isCollided(Tile tile1, Tile tile2) {
        return tile1.getX() == tile2.getX() && tile1.getY() == tile2.getY();
    }

    private void checkCollision() {
        Tile snakeHead = snake.getHead();
        Tile foodTile = food.getTile();

        // food - snake head
        if (isCollided(snakeHead, foodTile)) {
            food.randomizePosition();
            snake.getBody().add(new Tile(foodTile.getX(), foodTile.getY()));
        }

        // snake head - snake body
        for (int i = 0; i < snake.getBody().size(); i++) {
            Tile bodyTile = snake.getBody().get(i);

            if (isCollided(snakeHead, bodyTile)) {
                FinishGame();
                return;
            }
        }

        // snake head - board boundaries
        int snakeXPosition = snake.getWorldLocation()[0];
        int snakeYPosition = snake.getWorldLocation()[1];

        // check horizontal boundaries
        if (snakeXPosition < 0 || snakeXPosition > grid.getWidth()) {
            FinishGame();
            return;
        }
        // check vertical boundaries
        if (snakeYPosition < 0 || snakeYPosition > grid.getHeight()) {
            FinishGame();
            return;
        }
    }

    private void FinishGame() {
        isGameOver = true;
        snake.setIsAlive(false);
    }
}