package com.marsol0x.blockstacker;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Arrays;

public class Board extends Object {
    private int width;
    private int height;
    private Block[][] grid;

    public Board(int width, int height) {
        this.width = width;
        this.height = height;
        grid = new Block[height][width];
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
    
    private void updateScore(long totalRows) {
        if (totalRows <= 0) return;

        long score;
        ScoreState state = ScoreState.getScoreStateInstance();
        state.addRows(totalRows);
        
        if (totalRows == 1) {
            score = 100L;
        } else if (totalRows == 2) {
            score = 300L;
        } else if (totalRows == 3) {
            score = 500L;
        } else {
            score = 300L * totalRows;
        }
        state.addScore(score);
    }

    public boolean isEmpty(int x, int y) {
        if (!(x >= 0 && x < getWidth())) {
            return false;
        }
        if (!(y >= 0 && y < getHeight())) {
            return false;
        }

        if (grid[y][x] == null) {
            return true;
        } else {
            return false;
        }
    }

    public void setColor(Color c, int x, int y) {
        if (c == null) {
            grid[y][x] = null;
        } else {
            grid[y][x] = new Block(c, x * 20, y * 20);
        }
    }

    private boolean isRowFull(int row) {
        for (int i = 0; i < getWidth(); i++) {
            if (grid[row][i] == null) return false;
        }

        return true;
    }

    private void clearRow(int row) {
        Arrays.fill(grid[row], null);
    }

    private void collapseRow(int row) {
        for (int y = row; y > 0; y--) {
            grid[y] = grid[y - 1].clone();
            for (int x = 0; x < getWidth(); x++) {
                if (grid[y][x] != null) {
                    grid[y][x].incrementPosition(0, 20.0);
                }
            }
        }
        Arrays.fill(grid[0], null);
    }

    public void clearFullRows() {
        long totalRows = 0;
        for (int row = getHeight() - 1; row >= 0; row--) {
            if (isRowFull(row)) {
                clearRow(row);
                collapseRow(row);
                row++;
                totalRows++;
            }
        }
        updateScore(totalRows);
    }

    public void render(Graphics2D g2) {
        for (int y = 0; y < getHeight(); y++) {
            for (int x = 0; x < getWidth(); x++) {
                if (grid[y][x] != null) {
                    grid[y][x].paintBlock(g2);
                }
            }
        }
    }
}
