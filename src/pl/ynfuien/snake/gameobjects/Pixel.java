package pl.ynfuien.snake.gameobjects;

import java.awt.*;

import static pl.ynfuien.snake.Main.GRID_SIZE;
import static pl.ynfuien.snake.Main.SCALE;

public class Pixel {
    public int x;
    public int y;

    public Pixel(int x, int y)
    {
        this.x = x > GRID_SIZE ? GRID_SIZE : x;
        this.y = y > GRID_SIZE ? GRID_SIZE : y;
    }

    public void render(Graphics2D g, Color color)
    {
        g.setColor(color);
        g.fillRect(x * SCALE + x, y * SCALE + y, SCALE, SCALE);
//        Console.ForegroundColor = color;
//        Console.SetCursorPosition(x * 2, y);
//        Console.Write(_pixelChar);
    }

    public boolean equals(Pixel pixel)
    {
        return pixel.x == x && pixel.y == y;
    }
}
