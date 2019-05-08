package com.kata.bowlinggame.domain;

import java.util.List;

public class ScoreBoard {
    private List<String> scoreBoard;

    public ScoreBoard(List<String> scoreBoard) {
        this.scoreBoard = scoreBoard;
    }

    public List<String> getScoreBoard() {
        return scoreBoard;
    }

}
