package com.marsol0x.blockstacker;

import java.awt.Component;
import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class InfoPanel extends JPanel implements ScoreStateUser {
    private JLabel scoreTitleLabel = new JLabel("Score");
    private JLabel rowsTitleLabel = new JLabel("Rows");
    private JLabel scoreLabel = new JLabel("0");
    private JLabel rowsLabel = new JLabel("0");
    private JLabel pausedLabel = new JLabel("\n");
    
    private JLabel movementLabel = new JLabel("Arrows to move");
    private JLabel rotateLabel = new JLabel("Space to rotate");
    private JLabel pauseCommandLabel = new JLabel("Enter to pause");

    public InfoPanel() {
        super();
        setPreferredSize(new Dimension(150, 0));
        
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        
        scoreTitleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        scoreLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        rowsTitleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        rowsLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        pausedLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        movementLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        rotateLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        pauseCommandLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        add(Box.createVerticalStrut(10));
        add(scoreTitleLabel);
        add(scoreLabel);
        add(Box.createVerticalStrut(10));
        add(rowsTitleLabel);
        add(rowsLabel);
        add(Box.createVerticalStrut(10));
        add(pausedLabel);
        add(Box.createVerticalStrut(20));
        add(movementLabel);
        add(rotateLabel);
        add(pauseCommandLabel);
        
        ScoreState.addRegUpdate(this);
    }
    
    // ScoreStateUser interface implementation
    public void updateScore() {
        ScoreState state = ScoreState.getScoreStateInstance();
        scoreLabel.setText(String.valueOf(state.getScore()));
        rowsLabel.setText(String.valueOf(state.getRows()));
        
        if (state.isRunning()) {
            pausedLabel.setText("\n");
        } else {
            pausedLabel.setText("Paused");
        }
    }
}
