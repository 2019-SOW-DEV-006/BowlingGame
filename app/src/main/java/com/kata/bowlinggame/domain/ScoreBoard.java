package com.kata.bowlinggame.domain;

import java.util.List;

public class ScoreBoard {
    private List<String> scoreBoard;
    private int position;

    public ScoreBoard(List<String> scoreBoard) {
        this.scoreBoard = scoreBoard;
    }

    public List<String> board() {
        return scoreBoard;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
