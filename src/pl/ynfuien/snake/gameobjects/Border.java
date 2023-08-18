package pl.ynfuien.snake.gameobjects;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Border {
    private List<Pixel> borderPixels = new ArrayList<>();

    public Border(int size)
    {
        for (int i = 0; i < size; i++)
        {
            // Border in width
            borderPixels.add(new Pixel(i, 0));
            borderPixels.add(new Pixel(i, size - 1));

            // Border in height
            if (i == 0 || i == size - 1) continue;
            borderPixels.add(new Pixel(0, i));
            borderPixels.add(new Pixel(size - 1, i));
        }
    }

    public void render(Graphics2D g, Color color)
    {
        for (Pixel p : borderPixels)
        {
            p.render(g, color);
        }
    }

    public boolean contains(Pixel pixel)
    {
        for (Pixel p : borderPixels)
        {
            if (p.equals(pixel)) return true;
        }

        return false;
    }
}
