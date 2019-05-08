package com.kata.bowlinggame.model;

import java.util.List;

public class BowlingGame {
    private int rolls[] = new int[21];
    private int rollIndex = 0;

    public void pins(int pinsDown) {
        rolls[rollIndex++] = pinsDown;
    }

    public List<String> possiblePinsForSecondRoll() {
        return null;
    }

    public boolean isGameEnds() {
        return false;
    }

    public Integer score() {
        int score = 0;
        for (int pins : rolls) {
            score += pins;
        }
        return score;
    }
}
