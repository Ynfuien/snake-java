package pl.ynfuien.snake.gameobjects;


import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Snake {
    private List<Pixel> bodyPixels = new ArrayList<>();
    private Pixel headPixel;
    private int gridSize;

    public Snake(int size, int gridSize)
    {
        this.gridSize = gridSize;

        headPixel = new Pixel(gridSize / 2 + (size / 2), gridSize / 2 - 1);
        for (int i = size - 1; i > 0; i--)
        {
            bodyPixels.add(new Pixel(headPixel.x - i, headPixel.y));
        }
    }

    public void render(Graphics2D g, Color headColor, Color bodyColor)
    {
        headPixel.render(g, headColor);
        for (Pixel p : bodyPixels)
        {
            p.render(g, bodyColor);
        }
    }

    public boolean move(Direction direction, Border border)
    {
        int x = headPixel.x;
        int y = headPixel.y;

        if (direction == Direction.Up) y--;
        else if (direction == Direction.Right) x++;
        else if (direction == Direction.Down) y++;
        else if (direction == Direction.Left) x--;

        Pixel newHead = new Pixel(x, y);
        if (this.contains(newHead)) return false;
        if (border.contains(newHead)) return false;

        bodyPixels.add(headPixel);
        bodyPixels.remove(0);
        headPixel = newHead;
        return true;
    }

    public void grow()
    {
        Pixel newBody = new Pixel(bodyPixels.get(0).x, bodyPixels.get(0).y);
//        for (int i = 0; i < by; i++)
//        {
            bodyPixels.add(0, newBody);
//        }
    }

    public int getSize()
    {
        return bodyPixels.size() + 1;
    }

    public boolean contains(Pixel pixel)
    {
        if (headPixel.equals(pixel)) return true;
        for (Pixel p : bodyPixels)
        {
            if (p.equals(pixel)) return true;
        }

        return false;
    }

    public enum Direction
    {
        Up, Down, Left, Right
    }
}
