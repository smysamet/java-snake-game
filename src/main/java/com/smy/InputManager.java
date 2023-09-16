package com.smy;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JComponent;

public class InputManager extends JComponent implements KeyListener {

    private Snake snake;

    public InputManager(Snake snake, Grid grid) {
        this.snake = snake;
        addKeyListener(this);
        setFocusable(true);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_UP && snake.getVelocityY() != 1) {
            snake.setVelocity(0, -1);
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN && snake.getVelocityY() != -1) {
            snake.setVelocity(0, 1);
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT && snake.getVelocityX() != -1) {
            snake.setVelocity(1, 0);
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT && snake.getVelocityX() != 1) {
            snake.setVelocity(-1, 0);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}
