package com.tetris.game;

public class ScoreManager {
    private int score;
    private int totalLines;
    private int level;

    public ScoreManager() {
        score = 0;
        totalLines = 0;
        level = 1;
    }

    public void update(int clearedLines) {
        if(clearedLines <= 0)
            return;

        totalLines += clearedLines;
        score += calculatePoints(clearedLines);

        updateLevel();
    }

    private int calculatePoints(int clearedLines) {
        return switch(clearedLines) {
            case 1 -> 100;
            case 2 -> 300;
            case 3 -> 500;
            case 4 -> 800;
            default -> 0;
        };
    }

    private void updateLevel() {
        level = (totalLines / 10) + 1;
    }

    
    public int getScore() {
        return score;
    }

    public int getTotalLines() {
        return totalLines;
    }

    public int getLevel() {
        return level;
    }
}