package pl.ynfuien.snake;

import javax.swing.*;


public class Main {
    // Configuration variables
    public static final int GRID_SIZE = 32;
    public static final int SNAKE_SIZE = 5;
    public static final int SCALE = 20;
    public static final int TICK_TIME = 100;


    public static void main(String[] args) {
        Board board = new Board();
        JFrame f = new JFrame("Snake Java");
        f.add(board);
        f.pack();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setResizable(false);
        f.setLocationRelativeTo(null);
        f.setVisible(true);
    }
}
