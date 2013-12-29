package com.marsol0x.blockstacker;

import java.awt.Color;

public class Figure {
    public static final int S_FIGURE = 0;
    public static final int Z_FIGURE = 1;
    public static final int I_FIGURE = 2;
    public static final int O_FIGURE = 3;
    public static final int L_FIGURE = 4;
    public static final int J_FIGURE = 5;
    public static final int T_FIGURE = 6;

    private int figureGrid[][] = new int[4][2];
    private int rotation = 0;
    private int maxRotation = 0;
    private Color color;
    private int posX = 0;
    private int posY = 0;
    private Board board;

    public Figure(int fig, Board b, int x, int y) {
        posX = x;
        posY = y;
        board = b;
        switch (fig) {
        case S_FIGURE:
            figureGrid[0][0] = -1;
            figureGrid[0][1] = 0;

            figureGrid[1][0] = 0;
            figureGrid[1][1] = 0;

            figureGrid[2][0] = 0;
            figureGrid[2][1] = -1;

            figureGrid[3][0] = 1;
            figureGrid[3][1] = -1;

            color = Color.MAGENTA;
            maxRotation = 1;
            break;
        case Z_FIGURE:
            figureGrid[0][0] = -1;
            figureGrid[0][1] = -1;

            figureGrid[1][0] = 0;
            figureGrid[1][1] = -1;

            figureGrid[2][0] = 0;
            figureGrid[2][1] = 0;

            figureGrid[3][0] = 1;
            figureGrid[3][1] = 0;

            color = Color.GREEN;
            maxRotation = 1;
            break;
        case I_FIGURE:
            figureGrid[0][0] = -2;
            figureGrid[0][1] = 0;

            figureGrid[1][0] = -1;
            figureGrid[1][1] = 0;

            figureGrid[2][0] = 0;
            figureGrid[2][1] = 0;

            figureGrid[3][0] = 1;
            figureGrid[3][1] = 0;

            color = Color.RED;
            maxRotation = 1;
            break;
        case O_FIGURE:
            figureGrid[0][0] = 1;
            figureGrid[0][1] = 0;

            figureGrid[1][0] = 0;
            figureGrid[1][1] = 0;

            figureGrid[2][0] = 0;
            figureGrid[2][1] = 1;

            figureGrid[3][0] = 1;
            figureGrid[3][1] = 1;

            color = Color.YELLOW;
            maxRotation = 0;
            break;
        case L_FIGURE:
            figureGrid[0][0] = -1;
            figureGrid[0][1] = 0;

            figureGrid[1][0] = 0;
            figureGrid[1][1] = 0;

            figureGrid[2][0] = 1;
            figureGrid[2][1] = 0;

            figureGrid[3][0] = 1;
            figureGrid[3][1] = -1;

            color = Color.ORANGE;
            rotation = 2;
            maxRotation = 3;
            break;
        case J_FIGURE:
            figureGrid[0][0] = -1;
            figureGrid[0][1] = -1;

            figureGrid[1][0] = -1;
            figureGrid[1][1] = 0;

            figureGrid[2][0] = 0;
            figureGrid[2][1] = 0;

            figureGrid[3][0] = 1;
            figureGrid[3][1] = 0;

            color = Color.BLUE;
            rotation = 2;
            maxRotation = 3;
            break;
        case T_FIGURE:
            figureGrid[0][0] = -1;
            figureGrid[0][1] = 0;

            figureGrid[1][0] = 0;
            figureGrid[1][1] = 0;

            figureGrid[2][0] = 1;
            figureGrid[2][1] = 0;

            figureGrid[3][0] = 0;
            figureGrid[3][1] = -1;

            color = Color.CYAN;
            rotation = 2;
            maxRotation = 3;
            break;
        }
    }

    public Color getColor() {
        return color;
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
        if (board.isEmpty(x, y) || belongsToFigure(x, y)) {
            return true;
        }

        return false;
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
        paint(color);

        return true;
    }

    public boolean moveDown() {
        int newPosY = posY + 1;
        int x, y;

        for (int sqr = 0; sqr < 4; sqr++) {
            x = posX + relativeX(rotation, sqr);
            y = newPosY + relativeY(rotation, sqr);
            if (!canMove(x, y)) {
                return false;
            }
        }

        paint(null);
        posY = newPosY;
        paint(color);

        return true;
    }

    private int relativeX(int rot, int sqr) {
        switch (rot % 4) {
        case 0:
            return figureGrid[sqr][0];
        case 1:
            return figureGrid[sqr][1];
        case 2:
            return -figureGrid[sqr][0];
        case 3:
            return -figureGrid[sqr][1];
        }
        return 0;
    }

    private int relativeY(int rot, int sqr) {
        switch (rot % 4) {
        case 0:
            return figureGrid[sqr][1];
        case 1:
            return -figureGrid[sqr][0];
        case 2:
            return -figureGrid[sqr][1];
        case 3:
            return figureGrid[sqr][0];
        }
        return 0;
    }

    public void rotate() {
        int newRotation = rotation + 1;
        if (newRotation > maxRotation) {
            newRotation = 0;
        }

        int x, y;
        for (int i = 0; i < 4; i++) {
            x = posX + relativeX(newRotation, i);
            y = posY + relativeY(newRotation, i);
            if (!canMove(x, y)) return;
        }

        paint(null);
        rotation = newRotation;
        paint(color);
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
        paint(color);
    }

    private void paint(Color c) {
        int x, y;
        for (int i = 0; i < 4; i++) {
            x = posX + relativeX(rotation, i);
            y = posY + relativeY(rotation, i);
            board.setColor(c, x, y);
        }
    }
}
