package pl.ynfuien.snake;

import pl.ynfuien.snake.gameobjects.Berry;
import pl.ynfuien.snake.gameobjects.Border;
import pl.ynfuien.snake.gameobjects.Snake;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import static pl.ynfuien.snake.Main.*;

public class Board extends JPanel implements ActionListener {
    private final int SIZE;

    // Game variables
    private Border border;
    private Snake snake;
    private Berry berry;
    private Snake.Direction direction;
    private Snake.Direction newDirection;
    private Timer timer;
    private boolean gameOver = false;

    public Board() {
        SIZE = GRID_SIZE * Main.SCALE + (GRID_SIZE - 1);

        this.setPreferredSize(new Dimension(SIZE, SIZE));
        this.setBackground(GameColors.background.color);
        this.setFocusable(true);
        this.addKeyListener(new CustomKeyAdapter());

        border = new Border(GRID_SIZE);
        snake = new Snake(SNAKE_SIZE, GRID_SIZE);
        berry = new Berry(snake);
        direction = Snake.Direction.Right;
        newDirection = direction;

        timer = new Timer(TICK_TIME, this);
        timer.start();
    }

    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        // Clear
        g2d.setColor(GameColors.background.color);
        g2d.fillRect(0, 0, getWidth(), getHeight());

        if (gameOver) {
            int scale = (int) ((double) SCALE * 1.6);
            g2d.setFont(new Font("SansSerif", Font.BOLD, scale));
            FontMetrics fm = g2d.getFontMetrics();

            g2d.setColor(GameColors.gameOver.color);
            String gameOverString = "Game over!";
            g2d.drawString(gameOverString, (getWidth() - fm.stringWidth(gameOverString)) / 2, getHeight() / 2 - (scale * 2));

            int score = snake.getSize() - SNAKE_SIZE;
            String scoreNumberString = "Score: " + score;
            g2d.setColor(GameColors.scoreNumber.color);
            g2d.drawString(scoreNumberString, (getWidth() - fm.stringWidth(scoreNumberString)) / 2, getHeight() / 2 - scale);

            String scoreString = "Score: " + " ".repeat(String.valueOf(score).length() * 2);
            g2d.setColor(GameColors.score.color);
            g2d.drawString(scoreString, (getWidth() - fm.stringWidth(scoreString)) / 2, getHeight() / 2 - scale);


            border.render(g2d, GameColors.border.color);
            return;
        }

        // Render
        border.render(g2d, GameColors.border.color);
        snake.render(g2d, GameColors.snakeHead.color, GameColors.snakeBody.color);
        berry.render(g2d, GameColors.berry.color);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        direction = newDirection;

        // Move snake and check if it actually moved
        if (!snake.move(direction, border))
        {
            // Game over
            gameOver = true;
            repaint();
            timer.stop();
            return;
        }

        // Check if snake got the berry
        if (snake.contains(berry.position))
        {
            berry = new Berry(snake);
            snake.grow();
        }

        // render everything to user
        repaint();
    }


    private class CustomKeyAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            if (gameOver) return;
            int key = e.getKeyCode();

            switch (key) {
                case KeyEvent.VK_UP: case KeyEvent.VK_W:
                    if (direction == Snake.Direction.Down) break;
                    newDirection = Snake.Direction.Up;
                    break;
                case KeyEvent.VK_DOWN: case KeyEvent.VK_S:
                    if (direction == Snake.Direction.Up) break;
                    newDirection = Snake.Direction.Down;
                    break;
                case KeyEvent.VK_LEFT: case KeyEvent.VK_A:
                    if (direction == Snake.Direction.Right) break;
                    newDirection = Snake.Direction.Left;
                    break;
                case KeyEvent.VK_RIGHT: case KeyEvent.VK_D:
                    if (direction == Snake.Direction.Left) break;
                    newDirection = Snake.Direction.Right;
                    break;
            }
        }
    }
    enum GameColors {
        background(new Color(36, 36, 36)),
        snakeHead(new Color(231,108,1)),
        snakeBody(new Color(248,152,29)),
        berry(new Color(255, 85, 85)),
        border(new Color(85, 85, 85)),
        gameOver(new Color(255, 85, 85)),
        score(new Color(255, 255, 85)),
        scoreNumber(new Color(255, 170, 0));

        public final Color color;
        GameColors(Color color) {
            this.color = color;
        }
    }
}
