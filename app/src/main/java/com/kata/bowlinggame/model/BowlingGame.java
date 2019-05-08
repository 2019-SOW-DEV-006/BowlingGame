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
        return true;
    }

    public Integer score() {
        int score = 0;
        int position = 0;
        for (int frame = 0; frame < 10; frame++) {
            if (isStrike(position)) {
                score += 10 + strikeBonus(position);
                position++;
            } else if (isSpare(position)) {
                score += 10 + spareBonus(position);
                position += 2;
            } else {
                score += rolls[position] + rolls[position + 1];
                position += 2;
            }
        }
        return score;
    }

    private int spareBonus(int position) {
        return rolls[position + 2];
    }

    private boolean isSpare(int position) {
        return (rolls[position] + rolls[position + 1]) == 10;
    }

    private int strikeBonus(int position) {
        return rolls[position + 1] + rolls[position + 2];
    }

    private boolean isStrike(int position) {
        return rolls[position] == 10;
    }

}
