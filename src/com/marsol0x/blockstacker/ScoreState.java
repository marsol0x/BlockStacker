package com.marsol0x.blockstacker;

import java.util.ArrayList;

public class ScoreState {
    private static ScoreState instance = null;
    private ArrayList<ScoreStateUser> regUpdate = new ArrayList<ScoreStateUser>();
    
    private long score;
    private long rows;

    public static ScoreState getScoreStateInstance() {
        if (instance == null) {
            instance = new ScoreState();
        }
        return instance;
    }
    
    public static void resetScoreState() {
        instance = null;
    }
    
    private ScoreState() {
        score = 0;
        rows = 0;
    }
    
    public void addScore(long score) {
        this.score += score;
        updateRegistered();
    }

    public long getScore() {
        return score;
    }
    
    public void addRows(long rows) {
        this.rows += rows;
        updateRegistered();
    }

    public long getRows() {
        return rows;
    }
    
    public void addRegUpdate(ScoreStateUser panel) {
        regUpdate.add(panel);
    }
    
    private void updateRegistered() {
        for (ScoreStateUser p : regUpdate) {
            p.updateScore();
        }
    }
}
