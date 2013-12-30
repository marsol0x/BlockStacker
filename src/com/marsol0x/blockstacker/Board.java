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

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public boolean isEmpty(int x, int y) {
        if (!(x >= 0 && x < width)) {
            return false;
        }
        if (!(y >= 0 && y < height)) {
            return false;
        }

        if (grid[x][y] == null) {
            return true;
        } else {
            return false;
        }
    }

    public void setColor(Color c, int x, int y) {
        if (c == null) {
            grid[x][y] = null;
        } else {
            grid[x][y] = new Block(c, x * 20, y * 20);
        }
    }

    private boolean isRowFull(int row) {
        boolean rowFull = true;
        for (int i = 0; i < width; i++) {
            if (grid[i][row] == null) {
                rowFull = false;
                break;
            }
        }

        return rowFull;
    }

    private void clearRow(int row) {
        for (int i = 0; i < width; i++) {
            grid[i][row] = null;
        }
    }

    private void collapseRow(int row) {
        if (row > height) {
            return;
        }
        for (int i = 0; i < width; i++) {
            grid[i][row] = grid[i][row + 1];
            grid[i][row + 1] = null;
        }
    }

    public void clearFullRows() {
        for (int row = height - 1; row >= 0; row--) {
            if (isRowFull(row)) {
                clearRow(row);
                collapseRow(row);
            }
        }
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
