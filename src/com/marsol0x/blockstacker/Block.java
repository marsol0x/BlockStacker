package com.marsol0x.blockstacker;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

public class Block {
    private Color fillColor;
    private Color borderColor;
    public boolean playerControlled = false;
    
    public Block() {
        this.fillColor = Color.WHITE;
        this.borderColor = Color.WHITE;
    }
    
    public Block(Color c) {
        this.fillColor = c;
        this.borderColor = Color.BLACK;
    }

    public Color getFillColor() {
        return fillColor;
    }
    
    public void setFillColor(Color fillColor) {
        this.fillColor = fillColor;
    }
    
    public Color getBorderColor() {
        return borderColor;
    }
    
    public void paint(Graphics2D g, double x, double y) {
        Rectangle2D rect = new Rectangle2D.Double(x, y, 20, 20);
        BasicStroke stroke = new BasicStroke(2.0f, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_MITER);
        
        g.setPaint(getFillColor());
        g.fill(rect);
        g.setPaint(getBorderColor());
        g.setStroke(stroke);
        g.draw(rect);    
    }
}

