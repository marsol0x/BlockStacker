package com.marsol0x.blockstacker;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

public class Block {
    private Color fillColor;
    private double x;
    private double y;

    public Block(Color fillColor, double x, double y) {
        this.fillColor = fillColor;
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void paintBlock(Graphics2D g) {
        Rectangle2D rect = new Rectangle2D.Double(x, y, 20, 20);
        BasicStroke stroke = new BasicStroke(2.0f, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_MITER);

        g.setPaint(fillColor);
        g.fill(rect);
        g.setPaint(Color.BLACK);
        g.setStroke(stroke);
        g.draw(rect);    
    }
}

