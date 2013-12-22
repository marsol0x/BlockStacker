package com.marsol0x.blockstacker;

import java.awt.GridLayout;

import javax.swing.JFrame;

public class BlockStacker {

    public static void main(String[] args) {
        Game game = new Game();
//        Scoreboard score = new Scoreboard();
        JFrame frame = new JFrame("BlockStacker");
        
//        frame.setLayout(new GridLayout(1, 2));
        frame.setLayout(new GridLayout(1, 1));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setSize(500, 700);
        frame.setResizable(false);
        
//        frame.add(score);
        frame.add(game);
        frame.addKeyListener(game);

        frame.pack();
        frame.setVisible(true);
    }

}
