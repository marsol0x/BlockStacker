package com.marsol0x.blockstacker;

import java.awt.Color;
import java.awt.Graphics2D;

public class Board extends Object {
    private int width;
    private int height;
    private Block[][] grid;
    
    public Board(int width, int height) {
        this.width = width;
        this.height = height;
        grid = new Block[width][height];
    }
    
    public boolean isEmpty(int x, int y) {
        if ((x < 0 && x > width) && (y < 0 && y > height)) {
            return false;
        }

        if (grid[x][y] == null) {
            return true;
        } else {
            return false;
        }
    }
    
    public void setColor(Color c, int x, int y) {
        grid[x][y] = new Block(c, x * 20, y * 20);
    }

    public void render(Graphics2D g2) {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                if (grid[x][y] != null) {
                    grid[x][y].paintBlock(g2);
                }
            }
        }
    }
}
