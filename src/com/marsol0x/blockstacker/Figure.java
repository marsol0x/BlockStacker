package com.marsol0x.blockstacker;

import java.awt.Color;

public class Figure {
    private final FigureType figureType;
    private int rotation = 0;
    private int posX = 0;
    private int posY = 0;
    private Board board;

    private static final int rotX[][] = {{ 1, 0}, { 0, 1}, {-1, 0}, { 0,-1}};
    private static final int rotY[][] = {{ 0, 1}, {-1, 0}, { 0,-1}, { 1, 0}};

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
        for (int[] point : figureType.grid) {
            if ((posX + relativeX(point) == x) && (posY + relativeY(point) == y))
                return true;
        }
        return false;
    }

    private boolean canMove(int x, int y) {
        return board.isEmpty(x, y) || belongsToFigure(x, y);
    }

    public boolean canMove() {
        for (int[] point : figureType.grid) {
            if (!canMove(posX + relativeX(point), posY + relativeY(point)))
                return true;
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
        for (int[] point : figureType.grid) {
            x = newPosX + relativeX(point);
            y = posY + relativeY(point);
            if (!canMove(x, y)) {
                if (x < board.getWidth() && x >= 0) return true;
                return true;
            }
        }

        paint(null);
        posX = newPosX;
        paint(figureType.color);

        return true;
    }

    public boolean moveDown() {
        for (int[] point : figureType.grid) {
            if (!canMove(
                    posX + relativeX(point),
                    posY + 1 + relativeY(point)
            )) { return false; }
        }

        paint(null);
        posY++;
        paint(figureType.color);

        return true;
    }
    
    private int relativeX(int[] p) {
        final int[] spin = rotX[rotation];
        return spin[0]*p[0] + spin[1]*p[1];
    }
    
    private int relativeY(int[] p) {
        final int[] spin = rotY[rotation];
        return spin[0]*p[0] + spin[1]*p[1];
    }
    
    private int relativeX(int[] p, int rot) {
        final int oldRotation = rotation;
        rotation = rot;
        
        final int x = relativeX(p);
        rotation = oldRotation;
        return x;
    }
    
    private int relativeY(int[] p, int rot) {
        final int oldRotation = rotation;
        rotation = rot;
        
        final int y = relativeY(p);
        rotation = oldRotation;
        return y;
    }

    public void rotate() {
        int newRotation = rotation + 1;
        if (newRotation > figureType.maxRotation) {
            newRotation = 0;
        }

        for (int[] point : figureType.grid) {
            if (!canMove(posX + relativeX(point, newRotation), posY + relativeY(point, newRotation)))
                return;
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
        for (int[] point : figureType.grid) {
            board.setColor( color, posX + relativeX(point), posY + relativeY(point));
        }
    }
}
