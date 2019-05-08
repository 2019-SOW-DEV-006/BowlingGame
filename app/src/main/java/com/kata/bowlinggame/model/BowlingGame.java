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
        for (int position = 0; position < 20; position += 2) {
            Integer pinsDownInRow1 = rolls[position];
            Integer pinsDownInRow2 = rolls[position + 1];
            score += pinsDownInRow1 + pinsDownInRow2;

            if (isSpare(pinsDownInRow1, pinsDownInRow2)) {
                score += rolls[position + 2];
            }
        }
        return score;
    }

    private boolean isSpare(Integer pinsDownInRow1, Integer pinsDownInRow2) {
        return (pinsDownInRow1 + pinsDownInRow2) == 10;
    }
}
