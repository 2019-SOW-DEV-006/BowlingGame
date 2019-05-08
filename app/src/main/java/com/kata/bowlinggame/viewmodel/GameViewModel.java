package com.kata.bowlinggame.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.kata.bowlinggame.domain.PossiblePins;
import com.kata.bowlinggame.domain.ScoreBoard;
import com.kata.bowlinggame.model.BowlingGame;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GameViewModel {
    static final List<String> ALL_POSSIBLE_BUTTONS;
    static final List<String> NEW_SCORE_BOARD;

    private MutableLiveData<ScoreBoard> scoreBoard = new MutableLiveData<>();
    private MutableLiveData<PossiblePins> possiblePins = new MutableLiveData<>();

    static {
        NEW_SCORE_BOARD = new ArrayList<>(Arrays.asList(
                "", "",
                "", "",
                "", "",
                "", "",
                "", "",
                "", "",
                "", "",
                "", "",
                "", "",
                "", "",
                "", ""
        ));
        ALL_POSSIBLE_BUTTONS = new ArrayList<>(Arrays.asList(
                "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10"
        ));
    }

    private BowlingGame bowlingGame;
    private ScoreBoard currentScoreBoard;
    private int rollIndex = 0;

    GameViewModel(BowlingGame bowlingGame) {
        this.bowlingGame = bowlingGame;
        newGame();
    }

    LiveData<ScoreBoard> getScoreBoard() {
        return scoreBoard;
    }

    void newGame() {
        currentScoreBoard = new ScoreBoard(NEW_SCORE_BOARD);
        scoreBoard.setValue(currentScoreBoard);
        possiblePins.setValue(new PossiblePins(ALL_POSSIBLE_BUTTONS));
    }

    void roll(int pinsDown) {
        bowlingGame.pins(pinsDown);
        currentScoreBoard.board().set(rollIndex++, "" + pinsDown);
        possiblePins.setValue(new PossiblePins(bowlingGame.possiblePinsForSecondRoll()));
    }

    LiveData<PossiblePins> getPossiblePins() {
        return possiblePins;
    }

}
