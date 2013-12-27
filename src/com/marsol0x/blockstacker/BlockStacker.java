package com.marsol0x.blockstacker;

import java.awt.BorderLayout;

import javax.swing.JFrame;

public class BlockStacker {

    public static void main(String[] args) {
        Game game = new Game();
        JFrame frame = new JFrame("BlockStacker");
        
        frame.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        
        frame.add(game);
        frame.addKeyListener(game);

        frame.pack();
        frame.setVisible(true);
    }

}
