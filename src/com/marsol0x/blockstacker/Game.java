package com.marsol0x.blockstacker;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;
import javax.swing.Timer;

@SuppressWarnings("serial")
public class Game extends JPanel implements ActionListener, KeyListener {
    
    private Board board;
    private Timer timer;
    
    public Game() {
        super();
        setPreferredSize(new Dimension(200, 400));
        setDoubleBuffered(true);
        board = new Board();
        
        timer = new Timer(1000, this);
        timer.start();
    }
    
    public void paint(Graphics g) {
        super.paint(g);

        Graphics2D g2 = (Graphics2D) g;
        int width = board.getBoardWidth();
        int height = board.getBoardHeight();
        
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                Block b = board.getBlock(x, y);
                if (b == null) {
                    continue;
                }
                b.paint(g2, x * 20, y * 20);
            }
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        board.moveControlled(Direction.DOWN);
        repaint();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        Direction d = null;
        switch (e.getKeyCode()) {
        case KeyEvent.VK_LEFT:
            d = Direction.LEFT;
            break;
        case KeyEvent.VK_RIGHT:
            d = Direction.RIGHT;
            break;
        case KeyEvent.VK_DOWN:
            d = Direction.DOWN;
            break;
        }
        board.moveControlled(d);
        repaint();
        System.out.println("keyPressed: " + e.getKeyCode());
    }

    // Not using this
    @Override
    public void keyTyped(KeyEvent e) {}

    // Not using this
    @Override
    public void keyReleased(KeyEvent e) {}
}
