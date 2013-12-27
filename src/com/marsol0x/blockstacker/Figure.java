package com.marsol0x.blockstacker;

import java.awt.Color;

public class Figure {
    public static final int S_FIGURE = 1;
    public static final int Z_FIGURE = 2;
    public static final int I_FIGURE = 3;
    public static final int O_FIGURE = 4;
    public static final int L_FIGURE = 5;
    public static final int J_FIGURE = 6;
    public static final int T_FIGURE = 7;

    private int[][] figureGrid = new int[4][4];
    private int rotation = 0;
    private int maxRotation = 0;
    private Color color;
    private int posX = 0;
    private int posY = 0;
    private Board board;
    
    public Figure(int fig, Board b) {
        board = b;
        switch (fig) {
        case S_FIGURE:
            // [ ][ ][ ][ ]
            // [ ][ ][ ][ ]
            // [ ][X][X][ ]
            // [X][X][ ][ ]
            figureGrid[0][3] = 1;
            figureGrid[1][2] = 1;
            figureGrid[1][3] = 1;
            figureGrid[2][2] = 1;
            color = Color.MAGENTA;
            maxRotation = 4;
            break;
        case Z_FIGURE:
            // [ ][ ][ ][ ]
            // [ ][ ][ ][ ]
            // [X][X][ ][ ]
            // [ ][X][X][ ]
            figureGrid[0][2] = 1;
            figureGrid[1][2] = 1;
            figureGrid[1][3] = 1;
            figureGrid[2][3] = 1;
            color = Color.GREEN;
            maxRotation = 4;
            break;
        case I_FIGURE:
            // [ ][ ][ ][ ]
            // [ ][ ][ ][ ]
            // [X][X][X][X]
            // [ ][ ][ ][ ]
            figureGrid[0][2] = 1;
            figureGrid[1][2] = 1;
            figureGrid[2][2] = 1;
            figureGrid[3][2] = 1;
            color = Color.RED;
            maxRotation = 1;
            break;
        case O_FIGURE:
            // [ ][ ][ ][ ]
            // [ ][ ][ ][ ]
            // [ ][X][X][ ]
            // [ ][X][X][ ]
            figureGrid[1][2] = 1;
            figureGrid[2][2] = 1;
            figureGrid[1][3] = 1;
            figureGrid[2][3] = 1;
            color = Color.YELLOW;
            maxRotation = 0;
            break;
        case L_FIGURE:
            // [ ][ ][ ][ ]
            // [ ][ ][ ][ ]
            // [X][X][X][ ]
            // [X][ ][ ][ ]
            figureGrid[0][2] = 1;
            figureGrid[0][3] = 1;
            figureGrid[1][2] = 1;
            figureGrid[2][2] = 1;
            color = Color.ORANGE;
            maxRotation = 4;
            break;
        case J_FIGURE:
            // [ ][ ][ ][ ]
            // [ ][ ][ ][ ]
            // [X][X][X][ ]
            // [ ][ ][X][ ]
            figureGrid[0][2] = 1;
            figureGrid[1][2] = 1;
            figureGrid[2][2] = 1;
            figureGrid[2][3] = 1;
            color = Color.BLUE;
            maxRotation = 4;
            break;
        case T_FIGURE:
            // [ ][ ][ ][ ]
            // [ ][ ][ ][ ]
            // [X][X][X][ ]
            // [ ][X][ ][ ]
            figureGrid[0][2] = 1;
            figureGrid[1][2] = 1;
            figureGrid[2][2] = 1;
            figureGrid[1][3] = 1;
            color = Color.CYAN;
            maxRotation = 4;
            break;
        }
        paint(color);
    }

    public Color getColor() {
        return color;
    }
    
    private int relativeX(int rot, int x, int y) {
        switch (rot % 4) {
        case 0:
            return x;
        case 1:
            return y;
        case 2:
            return -x + 3;
        case 3:
            return -y + 3;
        }
        return 0;
    }
    
    private int relativeY(int rot, int x, int y) {
        switch (rot % 4) {
        case 0:
            return y;
        case 1:
            return -x + 3;
        case 2:
            return -y + 3;
        case 3:
            return x;
        }
        return 0;
    }
    
    public void rotate() {
        paint(null);
        rotation++;
        if (rotation > maxRotation) {
            rotation = 0;
        }
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

    public void paint(Color c) {
        int newX, newY;
        for (int y = 0; y < 4; y++) {
            for (int x = 0; x < 4; x++) {
                if (figureGrid[x][y] == 1) {
                    newX = relativeX(rotation, x, y);
                    newY = relativeY(rotation, x, y);
                    board.setColor(c, newX + posX, newY + posY);
                }
            }
        }
    }
}