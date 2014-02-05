package com.marsol0x.blockstacker;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;


@SuppressWarnings("serial")
class Game extends JPanel implements KeyListener, ActionListener {
    private Board board;
    private Figure figure;
    private boolean running;
    private final FigureType[] availFigures = FigureType.values();
    private Timer timer;
    private Random rand = new Random();

    public Game() {
        this(10, 20);
    }

    public Game(int width, int height) {
        super();
        setPreferredSize(new Dimension(width * 20, height * 20));
        setOpaque(true);
        setBackground(Color.BLACK);
        setDoubleBuffered(true);

        board = new Board(width, height);
        newFigure();

        timer = new Timer(1000, this);
        timer.start();
        running = true;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;
        board.render(g2);
    }

    private void newFigure() {
        int posY = 0;
        int newFig = rand.nextInt(7);
        if (newFig == 0 || newFig == 1) posY++;
        
        // check for game over state
        // if the x positions of 2, 3, 4, & 5 are filled on y position posY
        // then game over
        if (!(board.isEmpty(2, posY)
            && board.isEmpty(3, posY)
            && board.isEmpty(4, posY)
            && board.isEmpty(4, posY))) {
            triggerGameOver();
        }

        figure = null;
        figure = new Figure(availFigures[newFig], board, 4, posY);
        figure.paint();
    }

    private void triggerGameOver() {
        running = false;
        timer.stop();
        
        int new_game = JOptionPane.showConfirmDialog(this,
                "Play Again?",
                "Game Over",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.PLAIN_MESSAGE);
        
        if (new_game == JOptionPane.YES_OPTION) {
            System.out.println();
            ScoreState.resetScoreState();
            board.clearBoard();
            timer.start();
            running = true;
        } else {
            System.exit(0);
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        boolean stillMoving = true;
        
        if (!running) return;

        switch (e.getKeyCode()) {
        case KeyEvent.VK_SPACE:
            figure.rotate();
            break;
        case KeyEvent.VK_LEFT:
            stillMoving = figure.moveSide(0);
            break;
        case KeyEvent.VK_RIGHT:
            stillMoving = figure.moveSide(1);
            break;
        case KeyEvent.VK_DOWN:
            stillMoving = figure.moveDown();
            break;
        }

        if (!stillMoving) {
            board.clearFullRows();
            newFigure();
        }
        repaint();
    }
    @Override
    public void keyTyped(KeyEvent e) { }

    @Override
    public void keyReleased(KeyEvent e) { }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!figure.moveDown()) {
            board.clearFullRows();
            newFigure();
        }
        repaint();
    }
}
