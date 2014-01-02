package com.marsol0x.blockstacker;

import java.awt.Color;

public enum FigureType {
    S(new int[][]{{-1, 0}, { 0, 0}, { 0,-1}, { 1,-1}}, 0, 1, Color.MAGENTA),
    Z(new int[][]{{-1,-1}, { 0,-1}, { 0, 0}, { 1, 0}}, 0, 1, Color.GREEN),
    I(new int[][]{{-2, 0}, {-1, 0}, { 0, 0}, { 1, 0}}, 0, 1, Color.RED),
    O(new int[][]{{ 1, 0}, { 0, 0}, { 0, 1}, { 1, 1}}, 0, 0, Color.YELLOW),
    L(new int[][]{{-1, 0}, { 0, 0}, { 1, 0}, { 1,-1}}, 2, 3, Color.ORANGE),
    J(new int[][]{{-1,-1}, {-1, 0}, { 0, 0}, { 1, 0}}, 2, 3, Color.BLUE),
    T(new int[][]{{-1, 0}, { 0, 0}, { 1, 0}, { 0,-1}}, 2, 3, Color.CYAN);
    
    public final int[][] grid;
    public final int initialRotation;
    public final int maxRotation;
    public final Color color;
    
    private FigureType(int[][] grid, int initialRotation, int maxRotation, Color color) {
        this.grid = grid;
        this.initialRotation = initialRotation;
        this.maxRotation = maxRotation;
        this.color = color;
    }
}
