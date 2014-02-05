package com.marsol0x.blockstacker;

import java.awt.BorderLayout;

import javax.swing.JFrame;

public class BlockStacker {

    public static void main(String[] args) {
        Game game = new Game();
        InfoPanel infoPanel = new InfoPanel();
        JFrame frame = new JFrame("BlockStacker");

        frame.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        frame.add(game, BorderLayout.EAST);
        frame.addKeyListener(game);
        frame.add(infoPanel, BorderLayout.WEST);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

}
