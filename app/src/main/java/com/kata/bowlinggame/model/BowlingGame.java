package com.kata.bowlinggame.model;

import java.util.List;

import static com.kata.bowlinggame.viewmodel.GameViewModel.ALL_POSSIBLE_BUTTONS;

public class BowlingGame {
    private int rolls[] = new int[21];
    private int rollIndex = 0;

    public void pins(int pinsDown) {
        rolls[rollIndex++] = pinsDown;
    }

    public List<String> possiblePinsForSecondRoll() {
        return isNewFrame() ? ALL_POSSIBLE_BUTTONS : ALL_POSSIBLE_BUTTONS.subList(0, 11 - rolls[rollIndex - 1]);
    }

    public boolean isGameEnds() {
        int position = 0;
        int frame;
        boolean strikeBonusRequired = false;
        boolean spareBonusRequired = false;
        for (frame = 0; frame < 10; frame++) {
            strikeBonusRequired = isStrike(position);
            spareBonusRequired = isSpare(position);
            if (isStrike(position)) {
                position++;
            } else {
                position += 2;
            }
        }

        if (position <= rollIndex) {
            if (strikeBonusRequired) {
                position += 2;
            }
            if (spareBonusRequired) {
                position += 1;
            }
        }

        return position <= rollIndex;
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

    public void newGame() {
        rolls = new int[21];
        rollIndex = 0;
    }

    int[] getRolls() {
        return rolls;
    }

    int getRollIndex() {
        return rollIndex;
    }

    private boolean isNewFrame() {
        boolean newFrame = true;
        for (int currentIndex = 0; currentIndex < rollIndex; ) {
            if (isStrike(currentIndex)) {
                newFrame = true;
                currentIndex++;
            } else {
                if (isNextIndexAvailableFor(currentIndex)) {
                    newFrame = true;
                    currentIndex += 2;
                } else {
                    newFrame = false;
                    break;
                }
            }
        }
        return newFrame;
    }

    private boolean isNextIndexAvailableFor(int currentIndex) {
        return (currentIndex + 1) < rollIndex;
    }

    private int spareBonus(int position) {
        return rolls[position + 2];
    }

    private boolean isSpare(int position) {
        return position < 20 && (rolls[position] + rolls[position + 1]) == 10;
    }

    private int strikeBonus(int position) {
        return rolls[position + 1] + rolls[position + 2];
    }

    private boolean isStrike(int position) {
        return rolls[position] == 10;
    }
}
