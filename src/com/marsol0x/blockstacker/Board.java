package com.marsol0x.blockstacker;

import java.awt.Color;


public class Board {
    
    private int width = 10;
    private int height = 20;
    private Block[][] board = new Block[width][height];
    private Position[] controlled = new Position[4];
    private Block b = new Block(Color.RED);
    private Block pb = new Block(Color.RED);
    
    //   [][]
    // [][]
    private Position[] shapeS = {
        new Position(1, 0),
        new Position(2, 0),
        new Position(0, 1),
        new Position(1, 1)
    };
    
    // []
    // []
    // [][]
    private Position[] shapeL = {
        new Position(0, 0),
        new Position(0, 1),
        new Position(0, 2),
        new Position(1, 2)
    };
    
    // []
    // []
    // []
    // []
    private Position[] shapeI = {
        new Position(0, 0),
        new Position(0, 1),
        new Position(0, 2),
        new Position(0, 3)
    };
    
    //   []
    // [][][]
    private Position[] shapeT = {
       new Position(1, 0),
       new Position(0, 1),
       new Position(1, 1),
       new Position(2, 1),
    };
    
    // [][]
    // [][]
    private Position[] shapeO = {
            new Position(0, 0),
            new Position(1, 0),
            new Position(0, 1),
            new Position(1, 1),
    };
    
    public Board() {
        pb.playerControlled = true;
        controlled = shapeO.clone();

        for (Position i : controlled) {
            board[i.x][i.y] = pb;
        }
        
        for (Position i : shapeT) {
            board[i.x + 4][i.y + 10] = b;
        }
    }
    
    public Block[][] getBoard() {
        return board;
    }
    
    public Block getBlock(int x, int y) {

        if (x < 10 && x >= 0) {
            if (y < 20 && y >= 0) {
                return board[x][y];
            }
        }
        
        return null;
    }
    
    public int getBoardWidth() {
        return width;
    }
    
    public int getBoardHeight() {
        return height;
    }
    
    public void moveControlled(Direction d) {
        if (d == null) return;
        if (checkCollisionShift(controlled, d)) {
            for (int i = 0; i < controlled.length; i++) {
                Position p = controlled[i];
                board[p.x][p.y] = null;
                switch (d) {
                case LEFT:
                    controlled[i].x -= 1;
                    break;
                case RIGHT:
                    controlled[i].x += 1;
                    break;
                case DOWN:
                    controlled[i].y += 1;
                    break;
                }
            }
            for (Position i : controlled) {
                board[i.x][i.y] = pb;
            }
        }
    }
    
    private boolean isControlled(Position pos) {
        b = board[pos.x][pos.y];
        if (b == null) return false;
        return b.playerControlled;
    }
    
    private boolean checkCollisionShift(Position[] pos, Direction d) {
        Position tmp;
        for (Position i : pos) {
            switch (d) {
            case LEFT:
                tmp = new Position(i.x - 1, i.y);
                if (i.x - 1 < 0) return false;
                if (board[i.x - 1][i.y] != null && !isControlled(tmp)) return false;
                break;
            case RIGHT:
                tmp = new Position(i.x + 1, i.y);
                if (i.x + 1 >= width) return false;
                if (board[i.x + 1][i.y] != null && !isControlled(tmp)) return false;
                break;
            case DOWN:
                tmp = new Position(i.x, i.y + 1);
                if (i.y + 1 >= height) return false;
                if (board[i.x][i.y + 1] != null && !isControlled(tmp)) return false;
                break;
            default:
                return false;
            }
        }
        return true;
    }
    
    private class Position {
        int x;
        int y;
        
        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
