package com.marsol0x.blockstacker;

import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Scoreboard extends JPanel {

	public void paint(Graphics g) {
		Font font = new Font("Arial", Font.PLAIN, 12);
		g.setFont(font);

		g.drawString("Scoreboard", 1, 13);
	}
}
