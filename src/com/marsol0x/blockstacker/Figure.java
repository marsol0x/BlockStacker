package com.marsol0x.blockstacker;

import java.awt.Color;

public class Figure {
    private final FigureType figureType;
    private int rotation = 0;
    private int posX = 0;
    private int posY = 0;
    private Board board;

    public Figure(FigureType fig, Board b, int x, int y) {
        posX = x;
        posY = y;
        board = b;
        figureType = fig;
        rotation = figureType.initialRotation;
    }

    public Color getColor() {
        return figureType.color;
    }

    private boolean belongsToFigure(int x, int y) {
        int oldX, oldY;
        for (int sqr = 0; sqr < 4; sqr++) {
            oldX = posX + relativeX(rotation, sqr);
            oldY = posY + relativeY(rotation, sqr);
            if (oldX == x && oldY == y) return true;
        }
        return false;
    }

    private boolean canMove(int x, int y) {
        return board.isEmpty(x, y) || belongsToFigure(x, y);
    }

    public boolean canMove() {
        int x, y;
        for (int sqr = 0; sqr < 4; sqr++) {
            x = posX + relativeX(rotation, sqr);
            y = posY + relativeY(rotation, sqr);
            if (!canMove(x, y)) return false;
        }

        return true;
    }

    public boolean moveSide(int dir) {
        int newPosX = posX;
        if (dir == 0) {
            newPosX--;
        } else if (dir == 1) {
            newPosX++;
        } else {
            return false;
        }

        int x, y;
        for (int sqr = 0; sqr < 4; sqr++) {
            x = newPosX + relativeX(rotation, sqr);
            y = posY + relativeY(rotation, sqr);
            if (!canMove(x, y)) {
                // If I can't move, is the because of a wall or because I've hit another block?
                if (x < board.getWidth() && x >= 0) return true;
                // Stop here
                return true;
            }
        }

        paint(null);
        posX = newPosX;
        paint(figureType.color);

        return true;
    }

    public boolean moveDown() {
        for (int sqr = 0; sqr < 4; sqr++) {
            if (!canMove(
                    posX + relativeX(rotation, sqr),
                    posY + 1 + relativeY(rotation, sqr)
                )) { return false; }
        }

        paint(null);
        posY++;
        paint(figureType.color);

        return true;
    }

    private int relativeX(int rot, int sqr) {
        switch (rot % 4) {
        case 0:
            return figureType.grid[sqr][0];
        case 1:
            return figureType.grid[sqr][1];
        case 2:
            return -figureType.grid[sqr][0];
        case 3:
            return -figureType.grid[sqr][1];
        }
        return 0;
    }

    private int relativeY(int rot, int sqr) {
        switch (rot % 4) {
        case 0:
            return figureType.grid[sqr][1];
        case 1:
            return -figureType.grid[sqr][0];
        case 2:
            return -figureType.grid[sqr][1];
        case 3:
            return figureType.grid[sqr][0];
        }
        return 0;
    }

    public void rotate() {
        int newRotation = rotation + 1;
        if (newRotation > figureType.maxRotation) {
            newRotation = 0;
        }

        for (int i = 0; i < 4; i++) {
            if(!canMove(
                    posX + relativeX(newRotation, i),
                    posY + relativeY(newRotation, i)
            )) { return; }
        }

        paint(null);
        rotation = newRotation;
        paint(figureType.color);
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public void paint() {
        paint(figureType.color);
    }

    private void paint(Color color) {
        for (int i = 0; i < 4; i++) {
            board.setColor(
                    color,
                    posX + relativeX(rotation, i),
                    posY + relativeY(rotation, i)
            );
        }
    }
}
