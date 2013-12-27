package com.marsol0x.blockstacker;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;


@SuppressWarnings("serial")
class Game extends JPanel implements KeyListener {
    private Board board;
    private Figure figure;
    
    public Game() {
        this(10, 20);
    }
    
    public Game(int width, int height) {
        super();
        setPreferredSize(new Dimension(width * 20, height * 20));
        setOpaque(true);
        setBackground(Color.BLACK);
        board = new Board(width, height);
        figure = new Figure(Figure.S_FIGURE, board);
    }
    
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;
        
        board.render(g2);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            System.out.println("Space");
            figure.rotate();
            repaint();
        }
    }
    @Override
    public void keyTyped(KeyEvent e) { }

    @Override
    public void keyReleased(KeyEvent e) { }
}